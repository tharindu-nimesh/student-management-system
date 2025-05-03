package com.sms.dao;

import com.sms.model.Attendance;
import com.sms.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    private Connection connection;

    public AttendanceDAO() {
        connection = DBUtil.getConnection();
    }

    public void addAttendance(Attendance attendance) throws SQLException {
        String sql = "INSERT INTO attendance (student_id, course_id, attendance_date, status, remarks) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, attendance.getStudentId());
            statement.setInt(2, attendance.getCourseId());
            statement.setDate(3, new java.sql.Date(attendance.getAttendanceDate().getTime()));
            statement.setString(4, attendance.getStatus());
            statement.setString(5, attendance.getRemarks());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                attendance.setAttendanceId(generatedKeys.getInt(1));
            }
        }
    }

    public void updateAttendance(Attendance attendance) throws SQLException {
        String sql = "UPDATE attendance SET student_id=?, course_id=?, attendance_date=?, status=?, remarks=? WHERE attendance_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, attendance.getStudentId());
            statement.setInt(2, attendance.getCourseId());
            statement.setDate(3, new java.sql.Date(attendance.getAttendanceDate().getTime()));
            statement.setString(4, attendance.getStatus());
            statement.setString(5, attendance.getRemarks());
            statement.setInt(6, attendance.getAttendanceId());
            statement.executeUpdate();
        }
    }

    public void deleteAttendance(int attendanceId) throws SQLException {
        String sql = "DELETE FROM attendance WHERE attendance_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, attendanceId);
            statement.executeUpdate();
        }
    }

    public Attendance getAttendanceById(int attendanceId) throws SQLException {
        String sql = "SELECT a.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM attendance a " +
                "JOIN students s ON a.student_id = s.student_id " +
                "JOIN courses c ON a.course_id = c.course_id " +
                "WHERE a.attendance_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, attendanceId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return extractAttendanceFromResultSet(rs);
            }
        }
        return null;
    }

    public Attendance getAttendanceByStudentCourseDate(int studentId, int courseId, java.util.Date date) throws SQLException {
        String sql = "SELECT a.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM attendance a " +
                "JOIN students s ON a.student_id = s.student_id " +
                "JOIN courses c ON a.course_id = c.course_id " +
                "WHERE a.student_id=? AND a.course_id=? AND a.attendance_date=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setDate(3, new java.sql.Date(date.getTime()));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return extractAttendanceFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Attendance> getAttendanceByStudent(int studentId) throws SQLException {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT a.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM attendance a " +
                "JOIN students s ON a.student_id = s.student_id " +
                "JOIN courses c ON a.course_id = c.course_id " +
                "WHERE a.student_id=? ORDER BY a.attendance_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                attendanceList.add(extractAttendanceFromResultSet(rs));
            }
        }
        return attendanceList;
    }

    public List<Attendance> getAttendanceByCourse(int courseId) throws SQLException {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT a.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM attendance a " +
                "JOIN students s ON a.student_id = s.student_id " +
                "JOIN courses c ON a.course_id = c.course_id " +
                "WHERE a.course_id=? ORDER BY a.attendance_date DESC, s.last_name, s.first_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                attendanceList.add(extractAttendanceFromResultSet(rs));
            }
        }
        return attendanceList;
    }

    public List<Attendance> getAttendanceByDate(java.util.Date date) throws SQLException {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT a.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM attendance a " +
                "JOIN students s ON a.student_id = s.student_id " +
                "JOIN courses c ON a.course_id = c.course_id " +
                "WHERE a.attendance_date=? ORDER BY c.course_name, s.last_name, s.first_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                attendanceList.add(extractAttendanceFromResultSet(rs));
            }
        }
        return attendanceList;
    }

    public List<Attendance> getAttendanceByStudentAndCourse(int studentId, int courseId) throws SQLException {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT a.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM attendance a " +
                "JOIN students s ON a.student_id = s.student_id " +
                "JOIN courses c ON a.course_id = c.course_id " +
                "WHERE a.student_id=? AND a.course_id=? ORDER BY a.attendance_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                attendanceList.add(extractAttendanceFromResultSet(rs));
            }
        }
        return attendanceList;
    }

    public double getStudentAttendancePercentage(int studentId, int courseId) throws SQLException {
        String sql = "SELECT " +
                "COUNT(*) as total, " +
                "SUM(CASE WHEN status = 'Present' THEN 1 ELSE 0 END) as present " +
                "FROM attendance WHERE student_id=? AND course_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                int present = rs.getInt("present");
                if (total > 0) {
                    return ((double) present / total) * 100.0;
                }
            }
        }
        return 0.0;
    }

    private Attendance extractAttendanceFromResultSet(ResultSet rs) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setAttendanceId(rs.getInt("attendance_id"));
        attendance.setStudentId(rs.getInt("student_id"));
        attendance.setStudentName(rs.getString("student_name"));
        attendance.setCourseId(rs.getInt("course_id"));
        attendance.setCourseName(rs.getString("course_name"));
        attendance.setAttendanceDate(rs.getDate("attendance_date"));
        attendance.setStatus(rs.getString("status"));
        attendance.setRemarks(rs.getString("remarks"));
        attendance.setCreatedAt(rs.getTimestamp("created_at"));
        attendance.setUpdatedAt(rs.getTimestamp("updated_at"));
        return attendance;
    }
}

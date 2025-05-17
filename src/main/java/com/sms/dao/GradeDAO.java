package com.sms.dao;

import com.sms.model.Grade;
import com.sms.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    private Connection connection;

    public GradeDAO() {
        connection = DBUtil.getConnection();
    }

    public void addGrade(Grade grade) throws SQLException {
        String sql = "INSERT INTO grades (student_id, course_id, assignment_name, grade_value, max_grade, grade_date, comments) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, grade.getStudentId());
            statement.setInt(2, grade.getCourseId());
            statement.setString(3, grade.getAssignmentName());
            statement.setDouble(4, grade.getGradeValue());
            statement.setDouble(5, grade.getMaxGrade());
            statement.setDate(6, new java.sql.Date(grade.getGradeDate().getTime()));
            statement.setString(7, grade.getComments());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                grade.setGradeId(generatedKeys.getInt(1));
            }
        }
    }

    public void updateGrade(Grade grade) throws SQLException {
        String sql = "UPDATE grades SET student_id=?, course_id=?, assignment_name=?, grade_value=?, max_grade=?, grade_date=?, comments=? WHERE grade_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, grade.getStudentId());
            statement.setInt(2, grade.getCourseId());
            statement.setString(3, grade.getAssignmentName());
            statement.setDouble(4, grade.getGradeValue());
            statement.setDouble(5, grade.getMaxGrade());
            statement.setDate(6, new java.sql.Date(grade.getGradeDate().getTime()));
            statement.setString(7, grade.getComments());
            statement.setInt(8, grade.getGradeId());
            statement.executeUpdate();
        }
    }

    public void deleteGrade(int gradeId) throws SQLException {
        String sql = "DELETE FROM grades WHERE grade_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gradeId);
            statement.executeUpdate();
        }
    }

    public Grade getGradeById(int gradeId) throws SQLException {
        String sql = "SELECT g.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM grades g " +
                "JOIN students s ON g.student_id = s.student_id " +
                "JOIN courses c ON g.course_id = c.course_id " +
                "WHERE g.grade_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gradeId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return extractGradeFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Grade> getGradesByStudent(int studentId) throws SQLException {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM grades g " +
                "JOIN students s ON g.student_id = s.student_id " +
                "JOIN courses c ON g.course_id = c.course_id " +
                "WHERE g.student_id=? ORDER BY g.grade_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                grades.add(extractGradeFromResultSet(rs));
            }
        }
        return grades;
    }

    public List<Grade> getGradesByCourse(int courseId) throws SQLException {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM grades g " +
                "JOIN students s ON g.student_id = s.student_id " +
                "JOIN courses c ON g.course_id = c.course_id " +
                "WHERE g.course_id=? ORDER BY g.grade_date DESC, s.last_name, s.first_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                grades.add(extractGradeFromResultSet(rs));
            }
        }
        return grades;
    }

    public List<Grade> getGradesByStudentAndCourse(int studentId, int courseId) throws SQLException {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM grades g " +
                "JOIN students s ON g.student_id = s.student_id " +
                "JOIN courses c ON g.course_id = c.course_id " +
                "WHERE g.student_id=? AND g.course_id=? ORDER BY g.grade_date DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                grades.add(extractGradeFromResultSet(rs));
            }
        }
        return grades;
    }

    public double getStudentCourseAverage(int studentId, int courseId) throws SQLException {
        String sql = "SELECT AVG((grade_value/max_grade) * 100) as average " +
                "FROM grades WHERE student_id=? AND course_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getDouble("average");
            }
        }
        return 0.0;
    }

    private Grade extractGradeFromResultSet(ResultSet rs) throws SQLException {
        Grade grade = new Grade();
        grade.setGradeId(rs.getInt("grade_id"));
        grade.setStudentId(rs.getInt("student_id"));
        grade.setStudentName(rs.getString("student_name"));
        grade.setCourseId(rs.getInt("course_id"));
        grade.setCourseName(rs.getString("course_name"));
        grade.setAssignmentName(rs.getString("assignment_name"));
        grade.setGradeValue(rs.getDouble("grade_value"));
        grade.setMaxGrade(rs.getDouble("max_grade"));
        grade.setGradeDate(rs.getDate("grade_date"));
        grade.setComments(rs.getString("comments"));
        grade.setCreatedAt(rs.getTimestamp("created_at"));
        grade.setUpdatedAt(rs.getTimestamp("updated_at"));
        return grade;
    }

    public List<Grade> getAllGrades() throws SQLException {
        List<Grade> grades = new ArrayList<>();
        String sql = "SELECT g.*, CONCAT(s.first_name, ' ', s.last_name) as student_name, c.course_name " +
                "FROM grades g " +
                "JOIN students s ON g.student_id = s.student_id " +
                "JOIN courses c ON g.course_id = c.course_id " +
                "ORDER BY g.grade_date DESC";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                grades.add(extractGradeFromResultSet(rs));
            }
        }
        return grades;
    }

    public boolean isAssignmentExists(int studentId, int courseId, String assignmentName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM grades WHERE student_id = ? AND course_id = ? AND assignment_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setString(3, assignmentName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }


}

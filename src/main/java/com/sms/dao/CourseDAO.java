package com.sms.dao;

import com.sms.model.Course;
import com.sms.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Connection connection;

    public CourseDAO() {
        connection = DBUtil.getConnection();
    }

    public void addCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (course_code, course_name, description, credits, teacher_id, semester, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getCourseName());
            statement.setString(3, course.getDescription());
            statement.setInt(4, course.getCredits());
            statement.setInt(5, course.getTeacherId());
            statement.setString(6, course.getSemester());
            statement.setString(7, course.getStatus());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setCourseId(generatedKeys.getInt(1));
            }
        }
    }

    public void updateCourse(Course course) throws SQLException {
        String sql = "UPDATE courses SET course_code=?, course_name=?, description=?, credits=?, teacher_id=?, semester=?, status=? WHERE course_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getCourseName());
            statement.setString(3, course.getDescription());
            statement.setInt(4, course.getCredits());
            statement.setInt(5, course.getTeacherId());
            statement.setString(6, course.getSemester());
            statement.setString(7, course.getStatus());
            statement.setInt(8, course.getCourseId());
            statement.executeUpdate();
        }
    }

    public void deleteCourse(int courseId) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        }
    }

    public Course getCourseById(int courseId) throws SQLException {
        String sql = "SELECT c.*, CONCAT(t.first_name, ' ', t.last_name) as teacher_name " +
                "FROM courses c LEFT JOIN teachers t ON c.teacher_id = t.teacher_id " +
                "WHERE c.course_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return extractCourseFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, CONCAT(t.first_name, ' ', t.last_name) as teacher_name " +
                "FROM courses c LEFT JOIN teachers t ON c.teacher_id = t.teacher_id " +
                "ORDER BY c.course_code";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                courses.add(extractCourseFromResultSet(rs));
            }
        }
        return courses;
    }

    public List<Course> getCoursesByTeacher(int teacherId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, CONCAT(t.first_name, ' ', t.last_name) as teacher_name " +
                "FROM courses c LEFT JOIN teachers t ON c.teacher_id = t.teacher_id " +
                "WHERE c.teacher_id=? ORDER BY c.course_code";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teacherId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                courses.add(extractCourseFromResultSet(rs));
            }
        }
        return courses;
    }

    public List<Course> getCoursesByStudent(int studentId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, CONCAT(t.first_name, ' ', t.last_name) as teacher_name " +
                "FROM courses c " +
                "LEFT JOIN teachers t ON c.teacher_id = t.teacher_id " +
                "JOIN student_course sc ON c.course_id = sc.course_id " +
                "WHERE sc.student_id=? ORDER BY c.course_code";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                courses.add(extractCourseFromResultSet(rs));
            }
        }
        return courses;
    }

    public List<Course> getAvailableCoursesForStudent(int studentId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.*, CONCAT(t.first_name, ' ', t.last_name) as teacher_name " +
                "FROM courses c " +
                "LEFT JOIN teachers t ON c.teacher_id = t.teacher_id " +
                "WHERE c.course_id NOT IN " +
                "(SELECT course_id FROM student_course WHERE student_id = ?) " +
                "AND c.status = 'Active' " +
                "ORDER BY c.course_code";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                courses.add(extractCourseFromResultSet(rs));
            }
        }
        return courses;
    }

    public int getCourseCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM courses";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public void enrollStudent(int studentId, int courseId) throws SQLException {
        String sql = "INSERT INTO student_course (student_id, course_id, enrollment_date, status) VALUES (?, ?, CURDATE(), 'Enrolled')";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        }
    }

    public void unenrollStudent(int studentId, int courseId) throws SQLException {
        String sql = "DELETE FROM student_course WHERE student_id=? AND course_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        }
    }

    private Course extractCourseFromResultSet(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        course.setCourseCode(rs.getString("course_code"));
        course.setCourseName(rs.getString("course_name"));
        course.setDescription(rs.getString("description"));
        course.setCredits(rs.getInt("credits"));
        course.setTeacherId(rs.getInt("teacher_id"));
        course.setTeacherName(rs.getString("teacher_name"));
        course.setSemester(rs.getString("semester"));
        course.setStatus(rs.getString("status"));
        course.setCreatedAt(rs.getTimestamp("created_at"));
        course.setUpdatedAt(rs.getTimestamp("updated_at"));
        return course;
    }
}

package com.sms.dao;

import com.sms.model.Student;
import com.sms.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO() {
        connection = DBUtil.getConnection();
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (first_name, last_name, email, phone, date_of_birth, address, enrollment_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setDate(5, new java.sql.Date(student.getDateOfBirth().getTime()));
            statement.setString(6, student.getAddress());
            statement.setDate(7, new java.sql.Date(student.getEnrollmentDate().getTime()));
            statement.setString(8, student.getStatus());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setStudentId(generatedKeys.getInt(1));
            }
        }
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET first_name=?, last_name=?, email=?, phone=?, date_of_birth=?, address=?, enrollment_date=?, status=? WHERE student_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setDate(5, new java.sql.Date(student.getDateOfBirth().getTime()));
            statement.setString(6, student.getAddress());
            statement.setDate(7, new java.sql.Date(student.getEnrollmentDate().getTime()));
            statement.setString(8, student.getStatus());
            statement.setInt(9, student.getStudentId());
            statement.executeUpdate();
        }
    }

    public void deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.executeUpdate();
        }
    }

    public Student getStudentById(int studentId) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return extractStudentFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY last_name, first_name";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                students.add(extractStudentFromResultSet(rs));
            }
        }
        return students;
    }

    public List<Student> searchStudents(String searchTerm) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? ORDER BY last_name, first_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String term = "%" + searchTerm + "%";
            statement.setString(1, term);
            statement.setString(2, term);
            statement.setString(3, term);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                students.add(extractStudentFromResultSet(rs));
            }
        }
        return students;
    }

    public List<Student> getStudentsByCourse(int courseId) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT s.* FROM students s " +
                "JOIN student_course sc ON s.student_id = sc.student_id " +
                "WHERE sc.course_id = ? ORDER BY s.last_name, s.first_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                students.add(extractStudentFromResultSet(rs));
            }
        }
        return students;
    }

    public List<Student> getAvailableStudentsForCourse(int courseId) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students s " +
                "WHERE s.student_id NOT IN " +
                "(SELECT student_id FROM student_course WHERE course_id = ?) " +
                "AND s.status = 'Active' " +
                "ORDER BY s.last_name, s.first_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                students.add(extractStudentFromResultSet(rs));
            }
        }
        return students;
    }

    public int getStudentCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM students";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public List<Student> getRecentStudents(int limit) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY created_at DESC LIMIT ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, limit);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                students.add(extractStudentFromResultSet(rs));
            }
        }
        return students;
    }

    private Student extractStudentFromResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setStudentId(rs.getInt("student_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setEmail(rs.getString("email"));
        student.setPhone(rs.getString("phone"));
        student.setDateOfBirth(rs.getDate("date_of_birth"));
        student.setAddress(rs.getString("address"));
        student.setEnrollmentDate(rs.getDate("enrollment_date"));
        student.setStatus(rs.getString("status"));
        student.setCreatedAt(rs.getTimestamp("created_at"));
        student.setUpdatedAt(rs.getTimestamp("updated_at"));
        return student;
    }
}

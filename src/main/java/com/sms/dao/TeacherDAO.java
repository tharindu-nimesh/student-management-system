package com.sms.dao;

import com.sms.model.Teacher;
import com.sms.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private Connection connection;

    public TeacherDAO() {
        connection = DBUtil.getConnection();
    }

    public void addTeacher(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teachers (first_name, last_name, email, phone, department, hire_date, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getPhone());
            statement.setString(5, teacher.getDepartment());
            statement.setDate(6, new java.sql.Date(teacher.getHireDate().getTime()));
            statement.setString(7, teacher.getStatus());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                teacher.setTeacherId(generatedKeys.getInt(1));
            }
        }
    }

    public void updateTeacher(Teacher teacher) throws SQLException {
        String sql = "UPDATE teachers SET first_name=?, last_name=?, email=?, phone=?, department=?, hire_date=?, status=? WHERE teacher_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getPhone());
            statement.setString(5, teacher.getDepartment());
            statement.setDate(6, new java.sql.Date(teacher.getHireDate().getTime()));
            statement.setString(7, teacher.getStatus());
            statement.setInt(8, teacher.getTeacherId());
            statement.executeUpdate();
        }
    }

    public void deleteTeacher(int teacherId) throws SQLException {
        String sql = "DELETE FROM teachers WHERE teacher_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teacherId);
            statement.executeUpdate();
        }
    }

    public Teacher getTeacherById(int teacherId) throws SQLException {
        String sql = "SELECT * FROM teachers WHERE teacher_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, teacherId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return extractTeacherFromResultSet(rs);
            }
        }
        return null;
    }

    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers ORDER BY last_name, first_name";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                teachers.add(extractTeacherFromResultSet(rs));
            }
        }
        return teachers;
    }

    public List<Teacher> searchTeachers(String searchTerm) throws SQLException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR department LIKE ? ORDER BY last_name, first_name";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String term = "%" + searchTerm + "%";
            statement.setString(1, term);
            statement.setString(2, term);
            statement.setString(3, term);
            statement.setString(4, term);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                teachers.add(extractTeacherFromResultSet(rs));
            }
        }
        return teachers;
    }

    public int getTeacherCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM teachers";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private Teacher extractTeacherFromResultSet(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(rs.getInt("teacher_id"));
        teacher.setFirstName(rs.getString("first_name"));
        teacher.setLastName(rs.getString("last_name"));
        teacher.setEmail(rs.getString("email"));
        teacher.setPhone(rs.getString("phone"));
        teacher.setDepartment(rs.getString("department"));
        teacher.setHireDate(rs.getDate("hire_date"));
        teacher.setStatus(rs.getString("status"));
        teacher.setCreatedAt(rs.getTimestamp("created_at"));
        teacher.setUpdatedAt(rs.getTimestamp("updated_at"));
        return teacher;
    }

    public boolean isEmailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM teachers WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean isPhoneExists(String phone) throws SQLException {
        String sql = "SELECT COUNT(*) FROM teachers WHERE phone = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phone);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean isEmailExistsExcept(String email, int teacherId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM teachers WHERE email = ? AND teacher_id != ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setInt(2, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public boolean isPhoneExistsExcept(String phone, int teacherId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM teachers WHERE phone = ? AND teacher_id != ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phone);
            statement.setInt(2, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}

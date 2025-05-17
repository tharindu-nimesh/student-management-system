package com.sms.dao;

import com.sms.model.User;
import com.sms.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DBUtil.getConnection();
    }

    // Add new user
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, role, related_id, status, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getRelatedId());
            statement.setString(5, user.getStatus());
            statement.setString(6, user.getPhone());
            statement.setString(7, user.getEmail());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Update existing user
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username=?, password=?, role=?, related_id=?, status=?, phone=?, email=? WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getRelatedId());
            statement.setString(5, user.getStatus());
            statement.setString(6, user.getPhone());
            statement.setString(7, user.getEmail());
            statement.setInt(8, user.getUserId());
            statement.executeUpdate();
        }
    }

    // Delete user by ID
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }

    // Get user by ID
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        }
        return null;
    }

    // Get user by username
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        }
        return null;
    }

    // Get all users ordered by username
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY username";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        }
        return users;
    }

    // Update password by user ID
    public boolean updatePassword(int userId, String password) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, password); // TODO: Hash password in production
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        }
    }

    // Update password by username
    public boolean updatePasswordByUsername(String username, String password) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, password);  // TODO: Hash password in production
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        }
    }

    // Get users by role ordered by username
    public List<User> getUsersByRole(String role) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role=? ORDER BY username";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    users.add(extractUserFromResultSet(rs));
                }
            }
        }
        return users;
    }

    // Update last login timestamp for user
    public void updateLastLogin(int userId) throws SQLException {
        String sql = "UPDATE users SET last_login=CURRENT_TIMESTAMP WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }

    // Update user status
    public void updateUserStatus(int userId, String status) throws SQLException {
        String sql = "UPDATE users SET status=? WHERE user_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, userId);
            statement.executeUpdate();
        }
    }

    // Helper method to extract User object from ResultSet
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setRelatedId(rs.getInt("related_id"));
        user.setLastLogin(rs.getTimestamp("last_login"));
        user.setStatus(rs.getString("status"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}

package com.sms.service;

import com.sms.dao.UserDAO;
import com.sms.model.User;
import com.sms.util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public void addUser(User user) throws SQLException {
        // No hashing, store password as plain text
        userDAO.addUser(user);
    }

    public void updateUser(User user) throws SQLException {
        // No hashing, store password as plain text
        userDAO.updateUser(user);
    }

    public void deleteUser(int userId) throws SQLException {
        userDAO.deleteUser(userId);
    }

    public User getUserById(int userId) throws SQLException {
        return userDAO.getUserById(userId);
    }

    public User getUserByUsername(String username) throws SQLException {
        return userDAO.getUserByUsername(username);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public List<User> getUsersByRole(String role) throws SQLException {
        return userDAO.getUsersByRole(role);
    }

    public void updateLastLogin(int userId) throws SQLException {
        userDAO.updateLastLogin(userId);
    }

    public void updateUserStatus(int userId, String status) throws SQLException {
        userDAO.updateUserStatus(userId, status);
    }

    public boolean authenticate(String username, String password) throws SQLException {
        System.out.println("Attempting login for username: " + username);
        System.out.println("Password entered (not shown for security): [HIDDEN]");

        User user = userDAO.getUserByUsername(username);

        if (user != null) {
            System.out.println("User found in DB: " + user.getUsername());
            System.out.println("User status: " + user.getStatus());
            System.out.println("Password from DB: " + user.getPassword());

            boolean authenticated = password != null && password.equals(user.getPassword());
            System.out.println("Direct comparison result: " + authenticated);

            if (authenticated) {
                userDAO.updateLastLogin(user.getUserId());
            }
            return authenticated;
        } else {
            System.out.println("No user found with username: " + username);
            return false;
        }
    }

    // Method for testing BCrypt (no longer needed, but kept for reference)
    public void testBCryptVerification() {
        // Removed as we are no longer using BCrypt
        System.out.println("BCrypt test skipped as plain text passwords are used now.");
    }
}

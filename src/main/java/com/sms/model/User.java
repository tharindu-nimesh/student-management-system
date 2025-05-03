package com.sms.model;

import java.util.Date;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role;
    private int relatedId;
    private Date lastLogin;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    // Constructors
    public User() {}

    public User(int userId, String username, String password, String role,
                int relatedId, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.relatedId = relatedId;
        this.status = status;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(int relatedId) {
        this.relatedId = relatedId;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

package com.sms.dao;

import com.sms.model.Settings;
import com.sms.model.User;
import com.sms.util.DBUtil;

import java.sql.*;

public class SettingsDAO {
    private Connection connection;

    public SettingsDAO() {
        connection = DBUtil.getConnection();
    }

    public Settings getSettingsById(int id) throws SQLException {
        String sql = "SELECT * FROM settings WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractSettingsFromResultSet(rs);
            }
        }
        return null;
    }

    public boolean updateSettings(Settings s) throws SQLException {
        String sql = "UPDATE settings SET admin_name = ?, admin_email = ?, admin_phone = ?, institute_name = ?, logo_url = ?, academic_year = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, s.getAdminName());
            ps.setString(2, s.getAdminEmail());
            ps.setString(3, s.getAdminPhone());
            ps.setString(4, s.getInstituteName());
            ps.setString(5, s.getLogoUrl());
            ps.setString(6, s.getAcademicYear());
            ps.setInt(7, s.getId());

            int updated = ps.executeUpdate();

            if (updated > 0) {
                UserDAO userDAO = new UserDAO();
                User adminUser = userDAO.getUsersByRole("admin").stream().findFirst().orElse(null);

                if (adminUser != null) {
                    adminUser.setUsername(s.getAdminName());
                    adminUser.setEmail(s.getAdminEmail());
                    adminUser.setPhone(s.getAdminPhone());
                    userDAO.updateUser(adminUser);
                }
            }

            return updated > 0;
        }
    }

    public boolean updateProfile(Settings s) throws SQLException {
        String sql = "UPDATE settings SET admin_name = ?, admin_email = ?, admin_phone = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, s.getAdminName());
            ps.setString(2, s.getAdminEmail());
            ps.setString(3, s.getAdminPhone());
            ps.setInt(4, s.getId());

            int updated = ps.executeUpdate();

            if (updated > 0) {
                UserDAO userDAO = new UserDAO();
                User adminUser = userDAO.getUsersByRole("admin").stream().findFirst().orElse(null);

                if (adminUser != null) {
                    adminUser.setUsername(s.getAdminName());
                    adminUser.setEmail(s.getAdminEmail());
                    adminUser.setPhone(s.getAdminPhone());
                    userDAO.updateUser(adminUser);
                }
            }
            return updated > 0;
        }
    }

    public boolean updateUserPassword(int userId, String newPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        }
    }

    private Settings extractSettingsFromResultSet(ResultSet rs) throws SQLException {
        Settings s = new Settings();
        s.setId(rs.getInt("id"));
        s.setAdminName(rs.getString("admin_name"));
        s.setAdminEmail(rs.getString("admin_email"));
        s.setAdminPhone(rs.getString("admin_phone"));
        s.setInstituteName(rs.getString("institute_name"));
        s.setLogoUrl(rs.getString("logo_url"));
        s.setAcademicYear(rs.getString("academic_year"));
        return s;
    }
}

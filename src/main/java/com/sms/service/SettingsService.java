package com.sms.service;


import com.sms.dao.SettingsDAO;
import com.sms.model.Settings;

import java.sql.SQLException;

public class SettingsService {
    private SettingsDAO settingsDAO;

    public SettingsService() {
        settingsDAO = new SettingsDAO();
    }

    public Settings getSettings() throws SQLException {
        return settingsDAO.getSettingsById(1); // Fixed ID = 1
    }

    public void updateSettings(Settings settings) throws SQLException {
        settingsDAO.updateSettings(settings);
    }

    public void updateProfile(Settings settings) throws SQLException {
        settingsDAO.updateProfile(settings);
    }

    public void updatePassword(int userId, String newPassword) throws SQLException {
        settingsDAO.updateUserPassword(userId, newPassword);
    }
}

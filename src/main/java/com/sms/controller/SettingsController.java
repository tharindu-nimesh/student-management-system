package com.sms.controller;

import com.sms.model.Settings;
import com.sms.service.SettingsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/settings/*")
public class SettingsController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SettingsService settingsService;

    @Override
    public void init() {
        settingsService = new SettingsService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/view";
        }

        try {
            switch (action) {
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/view":
                default:
                    viewSettings(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        try {
            switch (action) {
                case "updateProfile":
                    updateProfile(request, response);
                    break;
                case "updatePassword":
                    updatePassword(request, response);
                    break;
                default:
                    viewSettings(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void viewSettings(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Settings settings = settingsService.getSettings();
        request.setAttribute("settings", settings);
        request.getRequestDispatcher("/WEB-INF/views/settings/settings.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Settings settings = settingsService.getSettings();
        request.setAttribute("settings", settings);
        request.getRequestDispatcher("/WEB-INF/views/settings_edit.jsp").forward(request, response);
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Settings settings = settingsService.getSettings();

            settings.setAdminName(request.getParameter("adminName"));
            settings.setAdminEmail(request.getParameter("adminEmail"));
            settings.setAdminPhone(request.getParameter("adminPhone"));

            settingsService.updateProfile(settings);

            request.setAttribute("successMessage", "Profile updated successfully!");
            request.setAttribute("returnUrl", request.getContextPath() + "/settings/view");
            request.setAttribute("returnLabel", "Back to Settings");
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Failed to update profile: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/settings/view");
            request.setAttribute("returnLabel", "Try Again");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("adminUsername");
            String newPassword = request.getParameter("newPassword");

            if (username == null || username.trim().isEmpty()) {
                throw new ServletException("Username cannot be empty.");
            }
            if (newPassword == null || newPassword.trim().isEmpty()) {
                throw new ServletException("Password cannot be empty.");
            }

            // TODO: Hash password here before storing for production
            // String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

            boolean updated = settingsService.updatePasswordByUsername(username, newPassword);

            if (updated) {
                request.setAttribute("successMessage", "Password updated successfully for user: " + username);
                request.setAttribute("returnUrl", request.getContextPath() + "/settings/view");
                request.setAttribute("returnLabel", "Back to Settings");
                request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "User not found or password update failed for username: " + username);
                request.setAttribute("returnUrl", request.getContextPath() + "/settings/view");
                request.setAttribute("returnLabel", "Try Again");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
            }

        } catch (SQLException | ServletException e) {
            request.setAttribute("errorMessage", "Failed to update password: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/settings/view");
            request.setAttribute("returnLabel", "Try Again");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }
}

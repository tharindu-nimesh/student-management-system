package com.sms.controller;

import com.sms.dao.SettingsDAO;
import com.sms.model.Settings;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/admin/details")
public class AdminController extends HttpServlet {

    private SettingsDAO settingsDAO;

    @Override
    public void init() throws ServletException {
        // Get database connection from somewhere (e.g. servlet context or JDBC utility)
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        settingsDAO = new SettingsDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int adminId = 1; // Or fetch dynamically based on session, etc.
            Settings settings = settingsDAO.getSettingsById(adminId);
            request.setAttribute("settings", settings);
            request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error while retrieving Admin Details", e);
        }
    }
}
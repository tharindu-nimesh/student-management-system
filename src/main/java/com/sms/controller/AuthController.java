package com.sms.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.sms.model.User;
import com.sms.service.UserService;

@WebServlet("/auth/*")
public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public void init() {
        userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "/login":
                showLoginForm(request, response);
                break;
            case "/logout":
                logout(request, response);
                break;
            default:
                showLoginForm(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "/login":
                authenticate(request, response);
                break;
            default:
                showLoginForm(request, response);
                break;
        }
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            boolean authenticated = userService.authenticate(username, password);

            if (authenticated) {
                User user = userService.getUserByUsername(username);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/dashboard");
            } else {
                request.setAttribute("errorMessage", "Invalid username or password");
                showLoginForm(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Database error. Please try again later.");
            showLoginForm(request, response);
            e.printStackTrace();
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/auth/login");
    }
}

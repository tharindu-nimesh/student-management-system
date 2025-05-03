package com.sms.controller;

import jakarta.servlet.*;
        import jakarta.servlet.http.*;
        import jakarta.servlet.annotation.*;
        import java.io.IOException;

@WebServlet("/settings")
public class settingsController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/settings/settings.jsp").forward(req, resp);
    }
}

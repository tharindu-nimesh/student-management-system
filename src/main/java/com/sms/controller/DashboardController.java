package com.sms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sms.model.Student;
import com.sms.model.Teacher;
import com.sms.service.StudentService;
import com.sms.service.TeacherService;
import com.sms.service.CourseService;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentService studentService;
    private TeacherService teacherService;
    private CourseService courseService;

    public void init() {
        studentService = new StudentService();
        teacherService = new TeacherService();
        courseService = new CourseService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get counts for dashboard statistics
            int studentCount = studentService.getStudentCount();
            int teacherCount = teacherService.getTeacherCount();
            int courseCount = courseService.getCourseCount();

            // Get recent students for dashboard
            List<Student> recentStudents = studentService.getRecentStudents(5);

            // Set attributes for the dashboard view
            request.setAttribute("studentCount", studentCount);
            request.setAttribute("teacherCount", teacherCount);
            request.setAttribute("courseCount", courseCount);
            request.setAttribute("recentStudents", recentStudents);

            // Forward to the dashboard view
            request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error loading dashboard data");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

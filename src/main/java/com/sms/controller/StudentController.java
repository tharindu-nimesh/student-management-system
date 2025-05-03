package com.sms.controller;

import com.sms.model.Student;
import com.sms.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students/*")
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentService studentService;

    public void init() {
        studentService = new StudentService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/list";
        }

        try {
            switch (action) {
                case "/view":
                    viewStudent(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/new":
                    showAddForm(request, response);
                    break;
                case "/list":
                default:
                    listStudents(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/list";
        }

        try {
            switch (action) {
                case "/add":
                    addStudent(request, response);
                    break;
                case "/update":
                    updateStudent(request, response);
                    break;
                case "/delete":
                    deleteStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        request.setAttribute("students", studentService.getAllStudents());
        request.getRequestDispatcher("/WEB-INF/views/students/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/students/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.getStudentById(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("/WEB-INF/views/students/edit.jsp").forward(request, response);
    }

    private void viewStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.getStudentById(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("/WEB-INF/views/students/view.jsp").forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // Extract form data and create a new student
        Student student = new Student();
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setEmail(request.getParameter("email"));
        student.setPhone(request.getParameter("phone"));
        student.setDateOfBirth(java.sql.Date.valueOf(request.getParameter("dateOfBirth")));
        student.setAddress(request.getParameter("address"));
        student.setEnrollmentDate(java.sql.Date.valueOf(request.getParameter("enrollmentDate")));
        student.setStatus(request.getParameter("status"));

        studentService.addStudent(student);
        request.setAttribute("successMessage", "Student added successfully");
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // Extract form data and update existing student
        int id = Integer.parseInt(request.getParameter("studentId"));
        Student student = studentService.getStudentById(id);
        student.setFirstName(request.getParameter("firstName"));
        student.setLastName(request.getParameter("lastName"));
        student.setEmail(request.getParameter("email"));
        student.setPhone(request.getParameter("phone"));
        student.setDateOfBirth(java.sql.Date.valueOf(request.getParameter("dateOfBirth")));
        student.setAddress(request.getParameter("address"));
        student.setEnrollmentDate(java.sql.Date.valueOf(request.getParameter("enrollmentDate")));
        student.setStatus(request.getParameter("status"));

        studentService.updateStudent(student);
        request.setAttribute("successMessage", "Student updated successfully");
        response.sendRedirect(request.getContextPath() + "/students");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deleteStudent(id);
        request.setAttribute("successMessage", "Student deleted successfully");
        response.sendRedirect(request.getContextPath() + "/students");
    }
}

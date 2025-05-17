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

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Extract key data for validation
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            // Validate email uniqueness
            if (email != null && !email.isEmpty() && studentService.isEmailExists(email)) {
                request.setAttribute("errorMessage", "A student with this email already exists. Email addresses must be unique.");
                request.setAttribute("returnUrl", request.getContextPath() + "/students/new");
                request.setAttribute("returnLabel", "Try Again");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Validate phone uniqueness
            if (phone != null && !phone.isEmpty() && studentService.isPhoneExists(phone)) {
                request.setAttribute("errorMessage", "A student with this phone number already exists. Phone numbers must be unique.");
                request.setAttribute("returnUrl", request.getContextPath() + "/students/new");
                request.setAttribute("returnLabel", "Try Again");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Extract form data and create a new student
            Student student = new Student();
            student.setFirstName(request.getParameter("firstName"));
            student.setLastName(request.getParameter("lastName"));
            student.setEmail(email);
            student.setPhone(phone);

            try {
                student.setDateOfBirth(java.sql.Date.valueOf(request.getParameter("dateOfBirth")));
                student.setEnrollmentDate(java.sql.Date.valueOf(request.getParameter("enrollmentDate")));
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD format.");
                request.setAttribute("returnUrl", request.getContextPath() + "/students/new");
                request.setAttribute("returnLabel", "Try Again");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            student.setAddress(request.getParameter("address"));
            student.setStatus(request.getParameter("status"));

            // Add student to database
            studentService.addStudent(student);

            // Set success attributes
            request.setAttribute("successMessage", "Student " + student.getFirstName() + " " + student.getLastName() + " was successfully added!");
            request.setAttribute("returnUrl", request.getContextPath() + "/students/new");
            request.setAttribute("returnLabel", "Add Another Student");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/students");
            request.setAttribute("secondaryLabel", "View All Students");

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

        } catch (SQLException e) {
            // Set error attributes
            request.setAttribute("errorMessage", "Failed to add student: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/students/new");
            request.setAttribute("returnLabel", "Try Again");

            // Forward to error page
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }


    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Extract form data and update existing student
            int id = Integer.parseInt(request.getParameter("studentId"));
            Student student = studentService.getStudentById(id);

            if (student == null) {
                throw new Exception("Student not found.");
            }

            student.setFirstName(request.getParameter("firstName"));
            student.setLastName(request.getParameter("lastName"));
            student.setEmail(request.getParameter("email"));
            student.setPhone(request.getParameter("phone"));
            student.setDateOfBirth(java.sql.Date.valueOf(request.getParameter("dateOfBirth")));
            student.setAddress(request.getParameter("address"));
            student.setEnrollmentDate(java.sql.Date.valueOf(request.getParameter("enrollmentDate")));
            student.setStatus(request.getParameter("status"));

            studentService.updateStudent(student);

            // Redirect to success page
            request.setAttribute("successMessage", "Student information was successfully updated!");
            request.setAttribute("returnUrl", request.getContextPath() + "/students");
            request.setAttribute("returnLabel", "Back to Students");

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

        } catch (Exception e) {
            // Set error attributes
            int studentId = 0;
            try {
                studentId = Integer.parseInt(request.getParameter("studentId"));
            } catch (NumberFormatException nfe) {
                // Ignore, we'll use 0 if we can't parse the ID
            }

            request.setAttribute("errorMessage", "Failed to update student: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/students/edit?id=" + studentId);
            request.setAttribute("returnLabel", "Try Again");

            // Forward to error page
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }



    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deleteStudent(id);
        request.setAttribute("successMessage", "Student deleted successfully");
        response.sendRedirect(request.getContextPath() + "/students");
    }
}

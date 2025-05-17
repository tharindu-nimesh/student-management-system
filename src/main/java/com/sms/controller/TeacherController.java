package com.sms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sms.model.Teacher;
import com.sms.model.Course;
import com.sms.service.TeacherService;
import com.sms.service.CourseService;

@WebServlet("/teachers/*")
public class TeacherController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeacherService teacherService;
    private CourseService courseService;

    public void init() {
        teacherService = new TeacherService();
        courseService = new CourseService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/new":
                    showAddForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteTeacher(request, response);
                    break;
                case "/view":
                    viewTeacher(request, response);
                    break;
                case "/search":
                    searchTeachers(request, response);
                    break;
                case "/courses":
                    viewTeacherCourses(request, response);
                    break;
                default:
                    listTeachers(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/add":
                    addTeacher(request, response);
                    break;
                case "/update":
                    updateTeacher(request, response);
                    break;
                case "/assign":
                    assignCourseToTeacher(request, response);
                    break;
                default:
                    listTeachers(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void listTeachers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Teacher> teachers = teacherService.getAllTeachers();
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("/WEB-INF/views/teachers/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/teachers/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherService.getTeacherById(teacherId);
        request.setAttribute("teacher", teacher);
        request.getRequestDispatcher("/WEB-INF/views/teachers/edit.jsp").forward(request, response);
    }

    private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Extract form data
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String department = request.getParameter("department");

            // Check for duplicate email
            if (email != null && !email.isEmpty() && teacherService.isEmailExists(email)) {
                request.setAttribute("errorMessage", "A teacher with this email already exists. Email addresses must be unique.");
                request.setAttribute("returnUrl", request.getContextPath() + "/teachers/new");
                request.setAttribute("returnLabel", "Try Again");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Check for duplicate phone
            if (phone != null && !phone.isEmpty() && teacherService.isPhoneExists(phone)) {
                request.setAttribute("errorMessage", "A teacher with this phone number already exists. Phone numbers must be unique.");
                request.setAttribute("returnUrl", request.getContextPath() + "/teachers/new");
                request.setAttribute("returnLabel", "Try Again");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Parse hire date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date hireDate = null;
            try {
                if (request.getParameter("hireDate") != null && !request.getParameter("hireDate").isEmpty()) {
                    hireDate = sdf.parse(request.getParameter("hireDate"));
                } else {
                    hireDate = new Date(); // Use current date as default
                }
            } catch (ParseException e) {
                request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD format.");
                request.setAttribute("returnUrl", request.getContextPath() + "/teachers/new");
                request.setAttribute("returnLabel", "Try Again");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            String status = request.getParameter("status");

            // Create teacher object
            Teacher teacher = new Teacher();
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            teacher.setEmail(email);
            teacher.setPhone(phone);
            teacher.setDepartment(department);
            teacher.setHireDate(hireDate);
            teacher.setStatus(status);

            // Add teacher to database
            teacherService.addTeacher(teacher);

            // Redirect to success page
            request.setAttribute("successMessage", "Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " was successfully added!");
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers/new");
            request.setAttribute("returnLabel", "Add Another Teacher");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/teachers");
            request.setAttribute("secondaryLabel", "View All Teachers");

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

        } catch (SQLException e) {
            // Set error attributes for database errors
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers/new");
            request.setAttribute("returnLabel", "Try Again");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        } catch (Exception e) {
            // Set error attributes for other errors
            request.setAttribute("errorMessage", "Failed to add teacher: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers/new");
            request.setAttribute("returnLabel", "Try Again");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }


    private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int teacherId = Integer.parseInt(request.getParameter("teacherId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String department = request.getParameter("department");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date hireDate = null;
            if (request.getParameter("hireDate") != null && !request.getParameter("hireDate").isEmpty()) {
                hireDate = sdf.parse(request.getParameter("hireDate"));
            }

            String status = request.getParameter("status");

            Teacher teacher = new Teacher();
            teacher.setTeacherId(teacherId);
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            teacher.setEmail(email);
            teacher.setPhone(phone);
            teacher.setDepartment(department);
            teacher.setHireDate(hireDate);
            teacher.setStatus(status);

            teacherService.updateTeacher(teacher);

            // Redirect to success page
            request.setAttribute("successMessage", "Teacher information was successfully updated!");
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers");
            request.setAttribute("returnLabel", "Back to Teachers");

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);
        } catch (Exception e) {
            // Set error attributes
            int teacherId = 0;
            try {
                teacherId = Integer.parseInt(request.getParameter("teacherId"));
            } catch (NumberFormatException nfe) {
                // Ignore, we'll use 0 if we can't parse the ID
            }

            request.setAttribute("errorMessage", "Failed to update teacher: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers/edit?id=" + teacherId);
            request.setAttribute("returnLabel", "Try Again");

            // Forward to error page
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }

    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int teacherId = Integer.parseInt(request.getParameter("id"));
            teacherService.deleteTeacher(teacherId);

            // Set success attributes
            request.setAttribute("successMessage", "Teacher deleted successfully");
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers");
            request.setAttribute("returnLabel", "Back to Teachers");

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle invalid ID format
            request.setAttribute("errorMessage", "Invalid teacher ID format");
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers");
            request.setAttribute("returnLabel", "Back to Teachers");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (SQLException e) {
            // Handle foreign key constraint or other SQL errors gracefully
            String message = e.getMessage();
            if (message != null && message.contains("foreign key constraint fails")) {
                request.setAttribute("errorMessage", "Cannot delete teacher: This teacher is assigned to one or more courses. Please reassign or delete those courses first.");
            } else {
                request.setAttribute("errorMessage", "Database error: " + message);
            }
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers");
            request.setAttribute("returnLabel", "Back to Teachers");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle any other unexpected errors
            request.setAttribute("errorMessage", "Failed to delete teacher: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers");
            request.setAttribute("returnLabel", "Back to Teachers");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }






    private void viewTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherService.getTeacherById(teacherId);
        request.setAttribute("teacher", teacher);
        request.getRequestDispatcher("/WEB-INF/views/teachers/view.jsp").forward(request, response);
    }

    private void searchTeachers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        List<Teacher> teachers = teacherService.searchTeachers(searchTerm);
        request.setAttribute("teachers", teachers);
        request.setAttribute("searchTerm", searchTerm);
        request.getRequestDispatcher("/WEB-INF/views/teachers/list.jsp").forward(request, response);
    }

    private void viewTeacherCourses(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("id"));
        Teacher teacher = teacherService.getTeacherById(teacherId);
        List<Course> courses = courseService.getCoursesByTeacher(teacherId);

        request.setAttribute("teacher", teacher);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/WEB-INF/views/teachers/courses.jsp").forward(request, response);
    }

    private void assignCourseToTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        try {
            int teacherId = Integer.parseInt(request.getParameter("teacherId"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));

            Course course = courseService.getCourseById(courseId);
            course.setTeacherId(teacherId);
            courseService.updateCourse(course);

            // Set success attributes
            request.setAttribute("successMessage", "Course was successfully assigned to teacher!");
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers/courses?id=" + teacherId);
            request.setAttribute("returnLabel", "View Teacher Courses");

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);
        } catch (Exception e) {
            // Set error attributes
            request.setAttribute("errorMessage", "Failed to assign course to teacher: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/teachers");
            request.setAttribute("returnLabel", "Back to Teachers");

            // Forward to error page
            request.getRequestDispatcher("src/main/webapp/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }


}

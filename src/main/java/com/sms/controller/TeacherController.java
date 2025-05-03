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
        } catch (SQLException | ParseException e) {
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

    private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String department = request.getParameter("department");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hireDate = null;
        if (request.getParameter("hireDate") != null && !request.getParameter("hireDate").isEmpty()) {
            hireDate = sdf.parse(request.getParameter("hireDate"));
        } else {
            hireDate = new Date(); // Use current date as default
        }

        String status = request.getParameter("status");

        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setEmail(email);
        teacher.setPhone(phone);
        teacher.setDepartment(department);
        teacher.setHireDate(hireDate);
        teacher.setStatus(status);

        teacherService.addTeacher(teacher);
        response.sendRedirect(request.getContextPath() + "/teachers");
    }

    private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
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
        response.sendRedirect(request.getContextPath() + "/teachers");
    }

    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("id"));
        teacherService.deleteTeacher(teacherId);
        response.sendRedirect(request.getContextPath() + "/teachers");
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

    private void assignCourseToTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        Course course = courseService.getCourseById(courseId);
        course.setTeacherId(teacherId);
        courseService.updateCourse(course);

        response.sendRedirect(request.getContextPath() + "/teachers/courses?id=" + teacherId);
    }
}

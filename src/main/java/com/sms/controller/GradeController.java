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

import com.sms.model.Grade;
import com.sms.model.Student;
import com.sms.model.Course;
import com.sms.service.GradeService;
import com.sms.service.StudentService;
import com.sms.service.CourseService;

@WebServlet("/grades/*")
public class GradeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GradeService gradeService;
    private StudentService studentService;
    private CourseService courseService;

    public void init() {
        gradeService = new GradeService();
        studentService = new StudentService();
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
                    deleteGrade(request, response);
                    break;
                case "/student":
                    viewStudentGrades(request, response);
                    break;
                case "/course":
                    viewCourseGrades(request, response);
                    break;
                default:
                    listGrades(request, response);
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
                    addGrade(request, response);
                    break;
                case "/update":
                    updateGrade(request, response);
                    break;
                default:
                    listGrades(request, response);
                    break;
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void listGrades(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Grade> grades = gradeService.getAllGrades();
        request.setAttribute("grades", grades);
        request.getRequestDispatcher("/WEB-INF/views/grades/list.jsp").forward(request, response);
    }


    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Student> students = studentService.getAllStudents();
        List<Course> courses = courseService.getAllCourses();

        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/WEB-INF/views/grades/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int gradeId = Integer.parseInt(request.getParameter("id"));
        Grade grade = gradeService.getGradeById(gradeId);
        List<Student> students = studentService.getAllStudents();
        List<Course> courses = courseService.getAllCourses();

        request.setAttribute("grade", grade);
        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/WEB-INF/views/grades/edit.jsp").forward(request, response);
    }

    private void addGrade(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String assignmentName = request.getParameter("assignmentName");
        double gradeValue = Double.parseDouble(request.getParameter("gradeValue"));
        double maxGrade = Double.parseDouble(request.getParameter("maxGrade"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date gradeDate = null;
        if (request.getParameter("gradeDate") != null && !request.getParameter("gradeDate").isEmpty()) {
            gradeDate = sdf.parse(request.getParameter("gradeDate"));
        } else {
            gradeDate = new Date(); // Use current date as default
        }

        String comments = request.getParameter("comments");

        Grade grade = new Grade();
        grade.setStudentId(studentId);
        grade.setCourseId(courseId);
        grade.setAssignmentName(assignmentName);
        grade.setGradeValue(gradeValue);
        grade.setMaxGrade(maxGrade);
        grade.setGradeDate(gradeDate);
        grade.setComments(comments);

        gradeService.addGrade(grade);

        String redirectParam = request.getParameter("redirect");
        if ("student".equals(redirectParam)) {
            response.sendRedirect(request.getContextPath() + "/grades/student?id=" + studentId);
        } else if ("course".equals(redirectParam)) {
            response.sendRedirect(request.getContextPath() + "/grades/course?id=" + courseId);
        } else {
            response.sendRedirect(request.getContextPath() + "/grades");
        }
    }

    private void updateGrade(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int gradeId = Integer.parseInt(request.getParameter("gradeId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String assignmentName = request.getParameter("assignmentName");
        double gradeValue = Double.parseDouble(request.getParameter("gradeValue"));
        double maxGrade = Double.parseDouble(request.getParameter("maxGrade"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date gradeDate = null;
        if (request.getParameter("gradeDate") != null && !request.getParameter("gradeDate").isEmpty()) {
            gradeDate = sdf.parse(request.getParameter("gradeDate"));
        }

        String comments = request.getParameter("comments");

        Grade grade = new Grade();
        grade.setGradeId(gradeId);
        grade.setStudentId(studentId);
        grade.setCourseId(courseId);
        grade.setAssignmentName(assignmentName);
        grade.setGradeValue(gradeValue);
        grade.setMaxGrade(maxGrade);
        grade.setGradeDate(gradeDate);
        grade.setComments(comments);

        gradeService.updateGrade(grade);

        String redirectParam = request.getParameter("redirect");
        if ("student".equals(redirectParam)) {
            response.sendRedirect(request.getContextPath() + "/grades/student?id=" + studentId);
        } else if ("course".equals(redirectParam)) {
            response.sendRedirect(request.getContextPath() + "/grades/course?id=" + courseId);
        } else {
            response.sendRedirect(request.getContextPath() + "/grades");
        }
    }

    private void deleteGrade(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int gradeId = Integer.parseInt(request.getParameter("id"));
        Grade grade = gradeService.getGradeById(gradeId);
        int studentId = grade.getStudentId();
        int courseId = grade.getCourseId();

        gradeService.deleteGrade(gradeId);

        String redirectParam = request.getParameter("redirect");
        if ("student".equals(redirectParam)) {
            response.sendRedirect(request.getContextPath() + "/grades/student?id=" + studentId);
        } else if ("course".equals(redirectParam)) {
            response.sendRedirect(request.getContextPath() + "/grades/course?id=" + courseId);
        } else {
            response.sendRedirect(request.getContextPath() + "/grades");
        }
    }

    private void viewStudentGrades(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.getStudentById(studentId);
        List<Grade> grades = gradeService.getGradesByStudent(studentId);
        List<Course> courses = courseService.getCoursesByStudent(studentId);

        request.setAttribute("student", student);
        request.setAttribute("grades", grades);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/WEB-INF/views/grades/list.jsp").forward(request, response);
    }

    private void viewCourseGrades(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.getCourseById(courseId);
        List<Grade> grades = gradeService.getGradesByCourse(courseId);
        List<Student> students = studentService.getStudentsByCourse(courseId);

        request.setAttribute("course", course);
        request.setAttribute("grades", grades);
        request.setAttribute("students", students);
        request.getRequestDispatcher("/WEB-INF/views/grades/list.jsp").forward(request, response);
    }
}

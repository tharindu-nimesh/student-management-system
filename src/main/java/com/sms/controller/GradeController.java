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
        } catch (SQLException e) {
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

    private void addGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
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

            // Get student and course names for better messaging (optional)
            String studentName = ""; // You could fetch this from a service if needed
            String courseName = ""; // You could fetch this from a service if needed

            // Set success attributes
            request.setAttribute("successMessage", "Grade for assignment '" + assignmentName + "' was successfully added!");

            // Determine the return URL based on the redirect parameter
            String redirectParam = request.getParameter("redirect");
            String returnUrl;
            String returnLabel;
            String secondaryUrl;
            String secondaryLabel;

            if ("student".equals(redirectParam)) {
                returnUrl = request.getContextPath() + "/grades/new?studentId=" + studentId;
                returnLabel = "Add Another Grade for this Student";
                secondaryUrl = request.getContextPath() + "/grades/student?id=" + studentId;
                secondaryLabel = "View Student Grades";
            } else if ("course".equals(redirectParam)) {
                returnUrl = request.getContextPath() + "/grades/new?courseId=" + courseId;
                returnLabel = "Add Another Grade for this Course";
                secondaryUrl = request.getContextPath() + "/grades/course?id=" + courseId;
                secondaryLabel = "View Course Grades";
            } else {
                returnUrl = request.getContextPath() + "/grades/new";
                returnLabel = "Add Another Grade";
                secondaryUrl = request.getContextPath() + "/grades";
                secondaryLabel = "View All Grades";
            }

            request.setAttribute("returnUrl", returnUrl);
            request.setAttribute("returnLabel", returnLabel);
            request.setAttribute("secondaryUrl", secondaryUrl);
            request.setAttribute("secondaryLabel", secondaryLabel);

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

        } catch (SQLException e) {
            // Set error attributes for database errors
            request.setAttribute("errorMessage", "Failed to add grade: " + e.getMessage());
            setErrorReturnAttributes(request);
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle invalid number format
            request.setAttribute("errorMessage", "Invalid number format. Please ensure all numeric fields have valid values.");
            setErrorReturnAttributes(request);
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (ParseException e) {
            // Handle date parsing errors
            request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD format for the grade date.");
            setErrorReturnAttributes(request);
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle any other unexpected errors
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            setErrorReturnAttributes(request);
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }

    // Helper method to set error return attributes
    private void setErrorReturnAttributes(HttpServletRequest request) {
        String redirectParam = request.getParameter("redirect");
        String studentId = request.getParameter("studentId");
        String courseId = request.getParameter("courseId");

        String returnUrl;
        String secondaryUrl;

        if ("student".equals(redirectParam) && studentId != null) {
            returnUrl = request.getContextPath() + "/grades/new?studentId=" + studentId;
            secondaryUrl = request.getContextPath() + "/grades/student?id=" + studentId;
        } else if ("course".equals(redirectParam) && courseId != null) {
            returnUrl = request.getContextPath() + "/grades/new?courseId=" + courseId;
            secondaryUrl = request.getContextPath() + "/grades/course?id=" + courseId;
        } else {
            returnUrl = request.getContextPath() + "/grades/new";
            secondaryUrl = request.getContextPath() + "/grades";
        }

        request.setAttribute("returnUrl", returnUrl);
        request.setAttribute("returnLabel", "Try Again");
        request.setAttribute("secondaryUrl", secondaryUrl);
        request.setAttribute("secondaryLabel", "Back to Grades");
    }


    private void updateGrade(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
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

            boolean success = gradeService.updateGrade(grade);

            String redirectParam = request.getParameter("redirect");
            String returnUrl;

            if ("student".equals(redirectParam)) {
                returnUrl = request.getContextPath() + "/grades/student?id=" + studentId;
            } else if ("course".equals(redirectParam)) {
                returnUrl = request.getContextPath() + "/grades/course?id=" + courseId;
            } else {
                returnUrl = request.getContextPath() + "/grades";
            }

            if (success) {
                // Set success attributes
                request.setAttribute("successMessage", "Grade updated successfully!");
                request.setAttribute("returnUrl", returnUrl);
                request.setAttribute("returnLabel", "Return to Grades");

                // Optional secondary action
                request.setAttribute("secondaryUrl", request.getContextPath() + "/dashboard");
                request.setAttribute("secondaryLabel", "Back to Dashboard");

                // Forward to success page
                request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);
            } else {
                // Set error attributes
                request.setAttribute("errorMessage", "Failed to update grade. Please try again.");
                request.setAttribute("returnUrl", returnUrl);
                request.setAttribute("returnLabel", "Try Again");

                // Forward to error page
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Handle parsing errors
            request.setAttribute("errorMessage", "Invalid number format: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/grades");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        } catch (ParseException e) {
            // Handle date parsing errors
            request.setAttribute("errorMessage", "Invalid date format: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/grades");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle database errors
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/grades");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle any other unexpected errors
            request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/grades");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
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

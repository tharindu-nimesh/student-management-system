package com.sms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sms.model.Course;
import com.sms.model.Teacher;
import com.sms.model.Student;
import com.sms.service.CourseService;
import com.sms.service.TeacherService;
import com.sms.service.StudentService;

@WebServlet("/courses/*")
public class CourseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CourseService courseService;
    private TeacherService teacherService;
    private StudentService studentService;

    public void init() {
        courseService = new CourseService();
        teacherService = new TeacherService();
        studentService = new StudentService();
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
                    deleteCourse(request, response);
                    break;
                case "/view":
                    viewCourse(request, response);
                    break;
                case "/students":
                    viewCourseStudents(request, response);
                    break;
                default:
                    listCourses(request, response);
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
                    addCourse(request, response);
                    break;
                case "/update":
                    updateCourse(request, response);
                    break;
                case "/enroll":
                    enrollStudent(request, response);
                    break;
                case "/unenroll":
                    unenrollStudent(request, response);
                    break;
                default:
                    listCourses(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void listCourses(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Course> courses = courseService.getAllCourses();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/WEB-INF/views/courses/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Teacher> teachers = teacherService.getAllTeachers();
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("/WEB-INF/views/courses/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.getCourseById(courseId);
        List<Teacher> teachers = teacherService.getAllTeachers();

        request.setAttribute("course", course);
        request.setAttribute("teachers", teachers);
        request.getRequestDispatcher("/WEB-INF/views/courses/edit.jsp").forward(request, response);
    }

    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String courseCode = request.getParameter("courseCode");
            String courseName = request.getParameter("courseName");
            String description = request.getParameter("description");
            int credits = Integer.parseInt(request.getParameter("credits"));

            int teacherId = 0;
            if (request.getParameter("teacherId") != null && !request.getParameter("teacherId").isEmpty()) {
                teacherId = Integer.parseInt(request.getParameter("teacherId"));
            }

            String semester = request.getParameter("semester");
            String status = request.getParameter("status");

            Course course = new Course();
            course.setCourseCode(courseCode);
            course.setCourseName(courseName);
            course.setDescription(description);
            course.setCredits(credits);
            course.setTeacherId(teacherId);
            course.setSemester(semester);
            course.setStatus(status);

            courseService.addCourse(course);

            // Set success attributes
            request.setAttribute("successMessage", "Course " + course.getCourseName() + " was successfully added!");
            request.setAttribute("returnUrl", request.getContextPath() + "/courses/new");
            request.setAttribute("returnLabel", "Add Another Course");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/courses");
            request.setAttribute("secondaryLabel", "View All Courses");

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

        } catch (SQLException e) {
            // Set error attributes
            request.setAttribute("errorMessage", "Failed to add course: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/courses/new");
            request.setAttribute("returnLabel", "Try Again");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/courses");
            request.setAttribute("secondaryLabel", "Back to Courses");

            // Forward to error page
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle invalid number format
            request.setAttribute("errorMessage", "Invalid number format. Please ensure credits and teacher ID are valid numbers.");
            request.setAttribute("returnUrl", request.getContextPath() + "/courses/new");
            request.setAttribute("returnLabel", "Try Again");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/courses");
            request.setAttribute("secondaryLabel", "Back to Courses");

            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle any other unexpected errors
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/courses/new");
            request.setAttribute("returnLabel", "Try Again");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/courses");
            request.setAttribute("secondaryLabel", "Back to Courses");

            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }


    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String courseCode = request.getParameter("courseCode");
            String courseName = request.getParameter("courseName");
            String description = request.getParameter("description");
            int credits = Integer.parseInt(request.getParameter("credits"));

            int teacherId = 0;
            if (request.getParameter("teacherId") != null && !request.getParameter("teacherId").isEmpty()) {
                teacherId = Integer.parseInt(request.getParameter("teacherId"));
            }

            String semester = request.getParameter("semester");
            String status = request.getParameter("status");

            Course course = new Course();
            course.setCourseId(courseId);
            course.setCourseCode(courseCode);
            course.setCourseName(courseName);
            course.setDescription(description);
            course.setCredits(credits);
            course.setTeacherId(teacherId);
            course.setSemester(semester);
            course.setStatus(status);

            courseService.updateCourse(course);

            // Set success attributes
            request.setAttribute("successMessage", "Course " + courseName + " was successfully updated!");
            request.setAttribute("returnUrl", request.getContextPath() + "/courses/edit?id=" + courseId);
            request.setAttribute("returnLabel", "Edit Course Again");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/courses");
            request.setAttribute("secondaryLabel", "View All Courses");

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

        } catch (SQLException e) {
            // Set error attributes for database errors
            request.setAttribute("errorMessage", "Failed to update course: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/courses/edit?id=" + request.getParameter("courseId"));
            request.setAttribute("returnLabel", "Try Again");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/courses");
            request.setAttribute("secondaryLabel", "Back to Courses");

            // Forward to error page
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle invalid number format
            request.setAttribute("errorMessage", "Invalid number format. Please ensure course ID, credits, and teacher ID are valid numbers.");
            request.setAttribute("returnUrl", request.getContextPath() + "/courses/edit?id=" + request.getParameter("courseId"));
            request.setAttribute("returnLabel", "Try Again");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/courses");
            request.setAttribute("secondaryLabel", "Back to Courses");

            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (Exception e) {
            // Handle any other unexpected errors
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/courses/edit?id=" + request.getParameter("courseId"));
            request.setAttribute("returnLabel", "Try Again");
            request.setAttribute("secondaryUrl", request.getContextPath() + "/courses");
            request.setAttribute("secondaryLabel", "Back to Courses");

            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }


    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int courseId = Integer.parseInt(request.getParameter("id"));
            courseService.deleteCourse(courseId);

            // Set success attributes
            request.setAttribute("successMessage", "Course deleted successfully");
            request.setAttribute("returnUrl", request.getContextPath() + "/courses");
            request.setAttribute("returnLabel", "Back to Courses");

            // Forward to success page
            request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid course ID format.");
            request.setAttribute("returnUrl", request.getContextPath() + "/courses");
            request.setAttribute("returnLabel", "Back to Courses");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (SQLException e) {
            String message = e.getMessage();
            // Optionally, check for foreign key constraint error
            if (message != null && message.contains("foreign key constraint fails")) {
                request.setAttribute("errorMessage", "Cannot delete course: This course has enrolled students or related records. Remove those first.");
            } else {
                request.setAttribute("errorMessage", "Database error: " + message);
            }
            request.setAttribute("returnUrl", request.getContextPath() + "/courses");
            request.setAttribute("returnLabel", "Back to Courses");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Failed to delete course: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/courses");
            request.setAttribute("returnLabel", "Back to Courses");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }


    private void viewCourse(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.getCourseById(courseId);

        Teacher teacher = null;
        if (course.getTeacherId() > 0) {
            teacher = teacherService.getTeacherById(course.getTeacherId());
        }

        request.setAttribute("course", course);
        request.setAttribute("teacher", teacher);
        request.getRequestDispatcher("/WEB-INF/views/courses/view.jsp").forward(request, response);
    }

    private void viewCourseStudents(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.getCourseById(courseId);
        List<Student> enrolledStudents = studentService.getStudentsByCourse(courseId);
        List<Student> availableStudents = studentService.getAvailableStudentsForCourse(courseId);

        request.setAttribute("course", course);
        request.setAttribute("enrolledStudents", enrolledStudents);
        request.setAttribute("availableStudents", availableStudents);
        request.getRequestDispatcher("/WEB-INF/views/courses/students.jsp").forward(request, response);
    }

    private void enrollStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        courseService.enrollStudent(studentId, courseId);
        response.sendRedirect(request.getContextPath() + "/courses/students?id=" + courseId);
    }

    private void unenrollStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        courseService.unenrollStudent(studentId, courseId);
        response.sendRedirect(request.getContextPath() + "/courses/students?id=" + courseId);
    }
}

package com.sms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sms.model.Attendance;
import com.sms.model.Student;
import com.sms.model.Course;
import com.sms.service.AttendanceService;
import com.sms.service.StudentService;
import com.sms.service.CourseService;

@WebServlet("/attendance/*")
public class AttendanceController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AttendanceService attendanceService;
    private StudentService studentService;
    private CourseService courseService;

    public void init() {
        attendanceService = new AttendanceService();
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
                    deleteAttendance(request, response);
                    break;
                case "/student":
                    viewStudentAttendance(request, response);
                    break;
                case "/course":
                    viewCourseAttendance(request, response);
                    break;
                case "/date":
                    viewDateAttendance(request, response);
                    break;
                case "/take":
                    showTakeAttendanceForm(request, response);
                    break;
                default:
                    listAttendance(request, response);
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
                    addAttendance(request, response);
                    break;
                case "/update":
                    updateAttendance(request, response);
                    break;
                case "/take":
                    takeAttendance(request, response);
                    break;
                default:
                    listAttendance(request, response);
                    break;
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

    private void listAttendance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Use the service to get all attendance records
        List<Attendance> attendanceList = attendanceService.getAllAttendance();
        request.setAttribute("attendanceList", attendanceList);
        request.getRequestDispatcher("/WEB-INF/views/attendance/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Student> students = studentService.getAllStudents();
        List<Course> courses = courseService.getAllCourses();

        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/WEB-INF/views/attendance/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int attendanceId = Integer.parseInt(request.getParameter("id"));
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
        List<Student> students = studentService.getAllStudents();
        List<Course> courses = courseService.getAllCourses();

        request.setAttribute("attendance", attendance);
        request.setAttribute("students", students);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/WEB-INF/views/attendance/edit.jsp").forward(request, response);
    }

    private void addAttendance(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // Parse student ID with validation
            int studentId;
            try {
                studentId = Integer.parseInt(request.getParameter("studentId"));
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid student ID format");
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Parse course ID with validation
            int courseId;
            try {
                courseId = Integer.parseInt(request.getParameter("courseId"));
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid course ID format");
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Parse date with validation
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date attendanceDate;
            try {
                if (request.getParameter("attendanceDate") != null && !request.getParameter("attendanceDate").isEmpty()) {
                    attendanceDate = sdf.parse(request.getParameter("attendanceDate"));
                } else {
                    attendanceDate = new Date(); // Use current date as default
                }
            } catch (ParseException e) {
                request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD format.");
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Validate status
            String status = request.getParameter("status");
            if (status == null || status.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Attendance status cannot be empty");
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            String remarks = request.getParameter("remarks");

            // Create and set attendance object
            Attendance attendance = new Attendance();
            attendance.setStudentId(studentId);
            attendance.setCourseId(courseId);
            attendance.setAttendanceDate(attendanceDate);
            attendance.setStatus(status);
            attendance.setRemarks(remarks);

            // Add attendance with service
            try {
                attendanceService.addAttendance(attendance);

                // Set success attributes
                request.setAttribute("successMessage", "Attendance record added successfully");

                // Determine redirect URL based on the redirect parameter
                String redirectParam = request.getParameter("redirect");
                String returnUrl;
                String secondaryUrl = request.getContextPath() + "/attendance";
                String secondaryLabel = "View All Attendance";

                if ("student".equals(redirectParam)) {
                    returnUrl = request.getContextPath() + "/attendance/student?id=" + studentId;
                    request.setAttribute("returnLabel", "Back to Student Attendance");
                } else if ("course".equals(redirectParam)) {
                    returnUrl = request.getContextPath() + "/attendance/course?id=" + courseId;
                    request.setAttribute("returnLabel", "Back to Course Attendance");
                } else if ("date".equals(redirectParam)) {
                    returnUrl = request.getContextPath() + "/attendance/date?date=" + sdf.format(attendanceDate);
                    request.setAttribute("returnLabel", "Back to Date Attendance");
                } else {
                    returnUrl = request.getContextPath() + "/attendance";
                    request.setAttribute("returnLabel", "Back to Attendance");
                }

                request.setAttribute("returnUrl", returnUrl);
                request.setAttribute("secondaryUrl", secondaryUrl);
                request.setAttribute("secondaryLabel", secondaryLabel);

                request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

            } catch (SQLException e) {
                // Handle database errors
                request.setAttribute("errorMessage", "Database error: " + e.getMessage());
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.setAttribute("secondaryUrl", request.getContextPath() + "/dashboard");
                request.setAttribute("secondaryLabel", "Back to Dashboard");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Catch any other unexpected errors
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }


    private void updateAttendance(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // Parse attendance ID with validation
            int attendanceId;
            try {
                attendanceId = Integer.parseInt(request.getParameter("attendanceId"));
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid attendance ID format");
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Parse student ID with validation
            int studentId;
            try {
                studentId = Integer.parseInt(request.getParameter("studentId"));
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid student ID format");
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Parse course ID with validation
            int courseId;
            try {
                courseId = Integer.parseInt(request.getParameter("courseId"));
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid course ID format");
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Parse date with validation
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date attendanceDate;
            try {
                if (request.getParameter("attendanceDate") != null && !request.getParameter("attendanceDate").isEmpty()) {
                    attendanceDate = sdf.parse(request.getParameter("attendanceDate"));
                } else {
                    request.setAttribute("errorMessage", "Attendance date is required");
                    request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                    request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                    return;
                }
            } catch (ParseException e) {
                request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD format.");
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            // Validate status
            String status = request.getParameter("status");
            if (status == null || status.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Attendance status cannot be empty");
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
                return;
            }

            String remarks = request.getParameter("remarks");

            // Create and set attendance object
            Attendance attendance = new Attendance();
            attendance.setAttendanceId(attendanceId);
            attendance.setStudentId(studentId);
            attendance.setCourseId(courseId);
            attendance.setAttendanceDate(attendanceDate);
            attendance.setStatus(status);
            attendance.setRemarks(remarks);

            // Update attendance with service
            try {
                attendanceService.updateAttendance(attendance);

                // Set success attributes
                request.setAttribute("successMessage", "Attendance record updated successfully");

                // Determine redirect URL based on the redirect parameter
                String redirectParam = request.getParameter("redirect");
                String returnUrl;
                String secondaryUrl = request.getContextPath() + "/attendance";
                String secondaryLabel = "View All Attendance";

                if ("student".equals(redirectParam)) {
                    returnUrl = request.getContextPath() + "/attendance/student?id=" + studentId;
                    request.setAttribute("returnLabel", "Back to Student Attendance");
                } else if ("course".equals(redirectParam)) {
                    returnUrl = request.getContextPath() + "/attendance/course?id=" + courseId;
                    request.setAttribute("returnLabel", "Back to Course Attendance");
                } else if ("date".equals(redirectParam)) {
                    returnUrl = request.getContextPath() + "/attendance/date?date=" + sdf.format(attendanceDate);
                    request.setAttribute("returnLabel", "Back to Date Attendance");
                } else {
                    returnUrl = request.getContextPath() + "/attendance";
                    request.setAttribute("returnLabel", "Back to Attendance");
                }

                request.setAttribute("returnUrl", returnUrl);
                request.setAttribute("secondaryUrl", secondaryUrl);
                request.setAttribute("secondaryLabel", secondaryLabel);

                request.getRequestDispatcher("/WEB-INF/views/UX/success.jsp").forward(request, response);

            } catch (SQLException e) {
                // Handle database errors
                request.setAttribute("errorMessage", "Database error while updating attendance: " + e.getMessage());
                request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
                request.setAttribute("secondaryUrl", request.getContextPath() + "/dashboard");
                request.setAttribute("secondaryLabel", "Back to Dashboard");
                request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Catch any other unexpected errors
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            request.setAttribute("returnUrl", request.getContextPath() + "/attendance");
            request.getRequestDispatcher("/WEB-INF/views/UX/error.jsp").forward(request, response);
        }
    }


    private void deleteAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int attendanceId = Integer.parseInt(request.getParameter("id"));
        Attendance attendance = attendanceService.getAttendanceById(attendanceId);
        int studentId = attendance.getStudentId();
        int courseId = attendance.getCourseId();
        Date attendanceDate = attendance.getAttendanceDate();

        attendanceService.deleteAttendance(attendanceId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String redirectParam = request.getParameter("redirect");
        if ("student".equals(redirectParam)) {
            response.sendRedirect(request.getContextPath() + "/attendance/student?id=" + studentId);
        } else if ("course".equals(redirectParam)) {
            response.sendRedirect(request.getContextPath() + "/attendance/course?id=" + courseId);
        } else if ("date".equals(redirectParam)) {
            response.sendRedirect(request.getContextPath() + "/attendance/date?date=" + sdf.format(attendanceDate));
        } else {
            response.sendRedirect(request.getContextPath() + "/attendance");
        }
    }

    private void viewStudentAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.getStudentById(studentId);
        List<Attendance> attendanceList = attendanceService.getAttendanceByStudent(studentId);
        List<Course> courses = courseService.getCoursesByStudent(studentId);

        request.setAttribute("student", student);
        request.setAttribute("attendanceList", attendanceList);
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("/WEB-INF/views/attendance/student.jsp").forward(request, response);
    }

    private void viewCourseAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("id"));
        Course course = courseService.getCourseById(courseId);
        List<Attendance> attendanceList = attendanceService.getAttendanceByCourse(courseId);
        List<Student> students = studentService.getStudentsByCourse(courseId);

        request.setAttribute("course", course);
        request.setAttribute("attendanceList", attendanceList);
        request.setAttribute("students", students);
        request.getRequestDispatcher("/WEB-INF/views/attendance/course.jsp").forward(request, response);
    }

    private void viewDateAttendance(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dateStr = request.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        List<Attendance> attendanceList = new ArrayList<>();

        try {
            if (dateStr != null && !dateStr.isEmpty()) {
                try {
                    date = sdf.parse(dateStr);
                } catch (ParseException e) {
                    // Log the error
                    request.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD format.");
                    date = new Date(); // Use current date as fallback
                    dateStr = sdf.format(date);
                }
            } else {
                date = new Date(); // Use current date if no date provided
                dateStr = sdf.format(date);
            }

            attendanceList = attendanceService.getAttendanceByDate(date);
        } catch (SQLException e) {
            // Log the database error
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
        }

        request.setAttribute("date", dateStr);
        request.setAttribute("attendanceList", attendanceList);
        request.getRequestDispatcher("/WEB-INF/views/attendance/date.jsp").forward(request, response);
    }

    private void showTakeAttendanceForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = courseService.getCourseById(courseId);
        List<Student> students = studentService.getStudentsByCourse(courseId);

        request.setAttribute("course", course);
        request.setAttribute("students", students);
        request.setAttribute("attendanceDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.getRequestDispatcher("/WEB-INF/views/attendance/take.jsp").forward(request, response);
    }

    private void takeAttendance(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String[] studentIds = request.getParameterValues("studentId");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date attendanceDate = null;
        if (request.getParameter("attendanceDate") != null && !request.getParameter("attendanceDate").isEmpty()) {
            attendanceDate = sdf.parse(request.getParameter("attendanceDate"));
        } else {
            attendanceDate = new Date(); // Use current date as default
        }

        if (studentIds != null) {
            for (String studentIdStr : studentIds) {
                int studentId = Integer.parseInt(studentIdStr);
                String statusParam = "status_" + studentId;
                String remarksParam = "remarks_" + studentId;
                String status = request.getParameter(statusParam);
                String remarks = request.getParameter(remarksParam);

                Attendance attendance = new Attendance();
                attendance.setStudentId(studentId);
                attendance.setCourseId(courseId);
                attendance.setAttendanceDate(attendanceDate);
                attendance.setStatus(status);
                attendance.setRemarks(remarks);

                // Try to find existing attendance record for this student, course, and date
                Attendance existingAttendance = attendanceService.getAttendanceByStudentCourseDate(studentId, courseId, attendanceDate);

                if (existingAttendance != null) {
                    // Update existing record
                    attendance.setAttendanceId(existingAttendance.getAttendanceId());
                    attendanceService.updateAttendance(attendance);
                } else {
                    // Add new record
                    attendanceService.addAttendance(attendance);
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/attendance/course?id=" + courseId);
    }
}

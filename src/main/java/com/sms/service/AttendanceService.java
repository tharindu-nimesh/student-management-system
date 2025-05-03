package com.sms.service;

import com.sms.dao.AttendanceDAO;
import com.sms.model.Attendance;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AttendanceService {
    private AttendanceDAO attendanceDAO;

    public AttendanceService() {
        this.attendanceDAO = new AttendanceDAO();
    }

    public void addAttendance(Attendance attendance) throws SQLException {
        attendanceDAO.addAttendance(attendance);
    }

    public void updateAttendance(Attendance attendance) throws SQLException {
        attendanceDAO.updateAttendance(attendance);
    }

    public void deleteAttendance(int attendanceId) throws SQLException {
        attendanceDAO.deleteAttendance(attendanceId);
    }

    public Attendance getAttendanceById(int attendanceId) throws SQLException {
        return attendanceDAO.getAttendanceById(attendanceId);
    }

    public Attendance getAttendanceByStudentCourseDate(int studentId, int courseId, Date date) throws SQLException {
        return attendanceDAO.getAttendanceByStudentCourseDate(studentId, courseId, date);
    }

    public List<Attendance> getAttendanceByStudent(int studentId) throws SQLException {
        return attendanceDAO.getAttendanceByStudent(studentId);
    }

    public List<Attendance> getAttendanceByCourse(int courseId) throws SQLException {
        return attendanceDAO.getAttendanceByCourse(courseId);
    }

    public List<Attendance> getAttendanceByDate(Date date) throws SQLException {
        return attendanceDAO.getAttendanceByDate(date);
    }

    public List<Attendance> getAttendanceByStudentAndCourse(int studentId, int courseId) throws SQLException {
        return attendanceDAO.getAttendanceByStudentAndCourse(studentId, courseId);
    }

    public double getStudentAttendancePercentage(int studentId, int courseId) throws SQLException {
        return attendanceDAO.getStudentAttendancePercentage(studentId, courseId);
    }
}

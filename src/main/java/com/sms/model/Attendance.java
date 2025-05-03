package com.sms.model;

import java.util.Date;

public class Attendance {
    private int attendanceId;
    private int studentId;
    private String studentName;
    private int courseId;
    private String courseName;
    private Date attendanceDate;
    private String status;
    private String remarks;
    private Date createdAt;
    private Date updatedAt;

    // Constructors
    public Attendance() {}

    public Attendance(int attendanceId, int studentId, int courseId,
                      Date attendanceDate, String status, String remarks) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.attendanceDate = attendanceDate;
        this.status = status;
        this.remarks = remarks;
    }

    // Getters and Setters
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId=" + attendanceId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", attendanceDate=" + attendanceDate +
                ", status='" + status + '\'' +
                '}';
    }
}

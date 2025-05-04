package com.sms.service;

import com.sms.dao.GradeDAO;
import com.sms.model.Grade;

import java.sql.SQLException;
import java.util.List;

public class GradeService {
    private GradeDAO gradeDAO;

    public GradeService() {
        this.gradeDAO = new GradeDAO();
    }

    public void addGrade(Grade grade) throws SQLException {
        gradeDAO.addGrade(grade);
    }

    public void updateGrade(Grade grade) throws SQLException {
        gradeDAO.updateGrade(grade);
    }

    public void deleteGrade(int gradeId) throws SQLException {
        gradeDAO.deleteGrade(gradeId);
    }

    public Grade getGradeById(int gradeId) throws SQLException {
        return gradeDAO.getGradeById(gradeId);
    }

    public List<Grade> getGradesByStudent(int studentId) throws SQLException {
        return gradeDAO.getGradesByStudent(studentId);
    }

    public List<Grade> getGradesByCourse(int courseId) throws SQLException {
        return gradeDAO.getGradesByCourse(courseId);
    }

    public List<Grade> getGradesByStudentAndCourse(int studentId, int courseId) throws SQLException {
        return gradeDAO.getGradesByStudentAndCourse(studentId, courseId);
    }

    public double getStudentCourseAverage(int studentId, int courseId) throws SQLException {
        return gradeDAO.getStudentCourseAverage(studentId, courseId);
    }

    public List<Grade> getAllGrades() throws SQLException {
        return gradeDAO.getAllGrades();
    }

}

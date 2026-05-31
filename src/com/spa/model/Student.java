package com.spa.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores student academic details using encapsulated fields.
 */
public class Student extends Person {
    private String studentId;
    private String course;
    private double attendancePercentage;
    private double assignmentScore;
    private List<SubjectMark> subjectMarks;

    public Student(String studentId, String name, int age, String course) {
        super(name, age);
        this.studentId = studentId;
        this.course = course;
        this.subjectMarks = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public double getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(double assignmentScore) {
        this.assignmentScore = assignmentScore;
    }

    public List<SubjectMark> getSubjectMarks() {
        return subjectMarks;
    }

    public void addSubjectMark(SubjectMark subjectMark) {
        subjectMarks.add(subjectMark);
    }

    public void clearSubjectMarks() {
        subjectMarks.clear();
    }
}

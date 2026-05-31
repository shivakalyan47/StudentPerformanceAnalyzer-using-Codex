package com.spa.model;

/**
 * Represents one subject and the marks scored by the student.
 */
public class SubjectMark {
    private String subjectName;
    private int marks;

    public SubjectMark(String subjectName, int marks) {
        this.subjectName = subjectName;
        this.marks = marks;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}

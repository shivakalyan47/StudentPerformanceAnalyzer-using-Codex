package com.spa.service;

import com.spa.model.PerformanceResult;
import com.spa.model.Student;
import com.spa.model.SubjectMark;
import com.spa.util.ValidationException;

/**
 * Contains the core business logic for marks, grade, prediction, and suggestions.
 */
public class PerformanceAnalyzer {
    private static final double LOW_ATTENDANCE_LIMIT = 75.0;
    private static final double LOW_ASSIGNMENT_LIMIT = 60.0;
    private static final int LOW_SUBJECT_MARK_LIMIT = 50;

    public PerformanceResult analyze(Student student) throws ValidationException {
        validateStudent(student);

        PerformanceResult result = new PerformanceResult();
        int total = calculateTotal(student);
        double average = calculateAverage(total, student.getSubjectMarks().size());

        result.setTotalMarks(total);
        result.setAverageMarks(average);
        result.setGrade(calculateGrade(average));
        result.setPerformanceCategory(predictPerformance(average));
        addSuggestions(student, result);

        return result;
    }

    private void validateStudent(Student student) throws ValidationException {
        if (student == null) {
            throw new ValidationException("Please enter student details first.");
        }

        if (student.getSubjectMarks().isEmpty()) {
            throw new ValidationException("Please enter marks for at least one subject.");
        }

        if (student.getAttendancePercentage() < 0 || student.getAttendancePercentage() > 100) {
            throw new ValidationException("Attendance must be between 0 and 100.");
        }

        if (student.getAssignmentScore() < 0 || student.getAssignmentScore() > 100) {
            throw new ValidationException("Assignment score must be between 0 and 100.");
        }
    }

    private int calculateTotal(Student student) {
        int total = 0;
        for (SubjectMark subjectMark : student.getSubjectMarks()) {
            total += subjectMark.getMarks();
        }
        return total;
    }

    private double calculateAverage(int total, int subjectCount) {
        return (double) total / subjectCount;
    }

    private String calculateGrade(double average) {
        if (average >= 85) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 50) {
            return "C";
        } else if (average >= 35) {
            return "D";
        }
        return "F";
    }

    private String predictPerformance(double average) {
        if (average >= 85) {
            return "Excellent";
        } else if (average >= 70) {
            return "Good";
        } else if (average >= 50) {
            return "Average";
        }
        return "Poor";
    }

    private void addSuggestions(Student student, PerformanceResult result) {
        boolean hasSuggestion = false;

        if (student.getAttendancePercentage() < LOW_ATTENDANCE_LIMIT) {
            result.addSuggestion("Improve attendance by attending classes regularly.");
            hasSuggestion = true;
        }

        for (SubjectMark subjectMark : student.getSubjectMarks()) {
            if (subjectMark.getMarks() < LOW_SUBJECT_MARK_LIMIT) {
                result.addSuggestion("Focus more on " + subjectMark.getSubjectName() + " because the marks are low.");
                hasSuggestion = true;
            }
        }

        if (student.getAssignmentScore() < LOW_ASSIGNMENT_LIMIT) {
            result.addSuggestion("Complete assignments regularly and submit them on time.");
            hasSuggestion = true;
        }

        if (!hasSuggestion) {
            result.addSuggestion("Keep maintaining the same study routine and consistency.");
        }
    }
}

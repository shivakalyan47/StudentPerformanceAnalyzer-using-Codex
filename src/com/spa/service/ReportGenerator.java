package com.spa.service;

import com.spa.model.PerformanceResult;
import com.spa.model.Student;
import com.spa.model.SubjectMark;
import com.spa.util.AppConstants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Generates a text report and saves it using file handling.
 */
public class ReportGenerator {
    public String buildReport(Student student, PerformanceResult result) {
        StringBuilder report = new StringBuilder();

        report.append("========================================\n");
        report.append("        STUDENT PERFORMANCE REPORT       \n");
        report.append("========================================\n\n");
        report.append("Student ID       : ").append(student.getStudentId()).append("\n");
        report.append("Name             : ").append(student.getName()).append("\n");
        report.append("Age              : ").append(student.getAge()).append("\n");
        report.append("Course           : ").append(student.getCourse()).append("\n");
        report.append("Attendance       : ").append(String.format("%.2f", student.getAttendancePercentage())).append("%\n");
        report.append("Assignment Score : ").append(String.format("%.2f", student.getAssignmentScore())).append("%\n\n");

        report.append("Subject Marks:\n");
        for (SubjectMark subjectMark : student.getSubjectMarks()) {
            report.append("- ").append(subjectMark.getSubjectName()).append(": ")
                    .append(subjectMark.getMarks()).append("/100\n");
        }

        report.append("\nResult Summary:\n");
        report.append("Total Marks          : ").append(result.getTotalMarks()).append("\n");
        report.append("Average Marks        : ").append(String.format("%.2f", result.getAverageMarks())).append("\n");
        report.append("Grade                : ").append(result.getGrade()).append("\n");
        report.append("Performance Category : ").append(result.getPerformanceCategory()).append("\n\n");

        report.append("Improvement Suggestions:\n");
        for (String suggestion : result.getSuggestions()) {
            report.append("- ").append(suggestion).append("\n");
        }

        report.append("\nGenerated On: ")
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .append("\n");
        report.append("========================================\n");

        return report.toString();
    }

    public File saveReport(Student student, PerformanceResult result) throws IOException {
        File folder = new File(AppConstants.REPORT_FOLDER);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new IOException("Unable to create reports folder.");
        }

        String safeStudentId = student.getStudentId().replaceAll("[^a-zA-Z0-9_-]", "_");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        File reportFile = new File(folder, "report_" + safeStudentId + "_" + timestamp + ".txt");

        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write(buildReport(student, result));
        }

        return reportFile;
    }
}

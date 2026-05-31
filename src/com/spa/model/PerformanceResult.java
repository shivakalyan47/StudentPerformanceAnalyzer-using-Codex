package com.spa.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores calculated output such as total, average, grade, prediction, and suggestions.
 */
public class PerformanceResult {
    private int totalMarks;
    private double averageMarks;
    private String grade;
    private String performanceCategory;
    private List<String> suggestions;

    public PerformanceResult() {
        suggestions = new ArrayList<>();
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public double getAverageMarks() {
        return averageMarks;
    }

    public void setAverageMarks(double averageMarks) {
        this.averageMarks = averageMarks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPerformanceCategory() {
        return performanceCategory;
    }

    public void setPerformanceCategory(String performanceCategory) {
        this.performanceCategory = performanceCategory;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void addSuggestion(String suggestion) {
        suggestions.add(suggestion);
    }
}

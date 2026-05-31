package com.spa.ui;

import com.spa.model.PerformanceResult;
import com.spa.model.Student;
import com.spa.model.SubjectMark;
import com.spa.service.PerformanceAnalyzer;
import com.spa.service.ReportGenerator;
import com.spa.service.StudentManager;
import com.spa.util.AppConstants;
import com.spa.util.ValidationException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Student dashboard module that collects input, analyzes performance, and shows reports.
 */
public class DashboardFrame extends JFrame {
    private JTextField studentIdField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField courseField;
    private JTextField attendanceField;
    private JTextField assignmentField;
    private List<JTextField> subjectFields;
    private List<JSpinner> markSpinners;
    private JLabel totalValueLabel;
    private JLabel averageValueLabel;
    private JLabel gradeValueLabel;
    private JLabel categoryValueLabel;
    private JTextArea suggestionsArea;
    private JTextArea reportArea;

    private Student currentStudent;
    private PerformanceResult currentResult;
    private final StudentManager studentManager;
    private final PerformanceAnalyzer analyzer;
    private final ReportGenerator reportGenerator;

    public DashboardFrame() {
        studentManager = new StudentManager();
        analyzer = new PerformanceAnalyzer();
        reportGenerator = new ReportGenerator();
        subjectFields = new ArrayList<>();
        markSpinners = new ArrayList<>();

        setTitle(AppConstants.APP_NAME + " - Dashboard");
        setSize(1100, 720);
        setMinimumSize(new Dimension(950, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(createMainPanel());
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(18, 18));
        mainPanel.setBackground(AppConstants.BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createInputPanel(), BorderLayout.WEST);
        mainPanel.add(createResultPanel(), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(AppConstants.PRIMARY_DARK);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));

        JLabel titleLabel = new JLabel("Modern Student Dashboard");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(AppConstants.TITLE_FONT);

        JLabel subtitleLabel = new JLabel("Register, analyze marks, predict performance, and generate reports");
        subtitleLabel.setForeground(new Color(210, 224, 255));
        subtitleLabel.setFont(AppConstants.NORMAL_FONT);

        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.SOUTH);
        return headerPanel;
    }

    private JPanel createInputPanel() {
        JPanel card = createCardPanel(new BorderLayout(10, 10));
        card.setPreferredSize(new Dimension(405, 0));

        JLabel formTitle = new JLabel("Student & Marks Entry");
        formTitle.setFont(AppConstants.SUBTITLE_FONT);
        formTitle.setForeground(AppConstants.PRIMARY_DARK);
        card.add(formTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(AppConstants.CARD);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1;

        studentIdField = createTextField("Student ID");
        nameField = createTextField("Student Name");
        ageField = createTextField("Age");
        courseField = createTextField("Course / Class");
        attendanceField = createTextField("Attendance Percentage");
        assignmentField = createTextField("Assignment Score");

        addField(formPanel, gbc, studentIdField, 0);
        addField(formPanel, gbc, nameField, 1);
        addField(formPanel, gbc, ageField, 2);
        addField(formPanel, gbc, courseField, 3);
        addField(formPanel, gbc, attendanceField, 4);
        addField(formPanel, gbc, assignmentField, 5);

        JLabel marksLabel = new JLabel("Subject Marks");
        marksLabel.setFont(AppConstants.SUBTITLE_FONT);
        marksLabel.setForeground(AppConstants.PRIMARY_DARK);
        gbc.gridy = 6;
        formPanel.add(marksLabel, gbc);

        for (int index = 0; index < 5; index++) {
            JPanel row = createSubjectRow(index + 1);
            gbc.gridy = 7 + index;
            formPanel.add(row, gbc);
        }

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        card.add(scrollPane, BorderLayout.CENTER);
        card.add(createActionPanel(), BorderLayout.SOUTH);
        return card;
    }

    private JPanel createSubjectRow(int number) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(AppConstants.CARD);

        JTextField subjectField = createTextField("Subject " + number);
        JSpinner markSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        markSpinner.setFont(AppConstants.NORMAL_FONT);
        markSpinner.setBorder(BorderFactory.createTitledBorder("Marks"));

        subjectFields.add(subjectField);
        markSpinners.add(markSpinner);

        row.add(subjectField, BorderLayout.CENTER);
        row.add(markSpinner, BorderLayout.EAST);
        return row;
    }

    private JPanel createActionPanel() {
        JPanel actionPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        actionPanel.setBackground(AppConstants.CARD);

        JButton analyzeButton = createButton("Analyze Performance", AppConstants.PRIMARY);
        JButton reportButton = createButton("Save Report", AppConstants.SUCCESS);
        JButton sampleButton = createButton("Load Sample", AppConstants.WARNING);
        JButton clearButton = createButton("Clear", AppConstants.DANGER);

        analyzeButton.addActionListener(event -> analyzePerformance());
        reportButton.addActionListener(event -> saveReport());
        sampleButton.addActionListener(event -> loadSampleData());
        clearButton.addActionListener(event -> clearForm());

        actionPanel.add(analyzeButton);
        actionPanel.add(reportButton);
        actionPanel.add(sampleButton);
        actionPanel.add(clearButton);
        return actionPanel;
    }

    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new BorderLayout(16, 16));
        resultPanel.setBackground(AppConstants.BACKGROUND);

        JPanel summaryPanel = new JPanel(new GridLayout(2, 2, 14, 14));
        summaryPanel.setBackground(AppConstants.BACKGROUND);

        totalValueLabel = new JLabel("0");
        averageValueLabel = new JLabel("0.00");
        gradeValueLabel = new JLabel("-");
        categoryValueLabel = new JLabel("-");

        summaryPanel.add(createMetricCard("Total Marks", totalValueLabel));
        summaryPanel.add(createMetricCard("Average", averageValueLabel));
        summaryPanel.add(createMetricCard("Grade", gradeValueLabel));
        summaryPanel.add(createMetricCard("Performance", categoryValueLabel));

        JPanel lowerPanel = new JPanel(new GridLayout(1, 2, 16, 16));
        lowerPanel.setBackground(AppConstants.BACKGROUND);

        suggestionsArea = createTextArea("Suggestions will appear here after analysis.");
        reportArea = createTextArea("Generated student report will appear here.");

        lowerPanel.add(createTextCard("Improvement Suggestions", suggestionsArea));
        lowerPanel.add(createTextCard("Student Report", reportArea));

        resultPanel.add(summaryPanel, BorderLayout.NORTH);
        resultPanel.add(lowerPanel, BorderLayout.CENTER);
        return resultPanel;
    }

    private JPanel createMetricCard(String title, JLabel valueLabel) {
        JPanel card = createCardPanel(new BorderLayout(8, 8));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(AppConstants.NORMAL_FONT);
        titleLabel.setForeground(new Color(87, 96, 116));

        valueLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 30));
        valueLabel.setForeground(AppConstants.PRIMARY_DARK);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }

    private JPanel createTextCard(String title, JTextArea textArea) {
        JPanel card = createCardPanel(new BorderLayout(8, 8));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(AppConstants.SUBTITLE_FONT);
        titleLabel.setForeground(AppConstants.PRIMARY_DARK);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(new JScrollPane(textArea), BorderLayout.CENTER);
        return card;
    }

    private JPanel createCardPanel(java.awt.LayoutManager layoutManager) {
        JPanel panel = new JPanel(layoutManager);
        panel.setBackground(AppConstants.CARD);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 231, 246)),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));
        return panel;
    }

    private JTextField createTextField(String title) {
        JTextField textField = new JTextField();
        textField.setFont(AppConstants.NORMAL_FONT);
        textField.setBorder(BorderFactory.createTitledBorder(title));
        return textField;
    }

    private JTextArea createTextArea(String defaultText) {
        JTextArea textArea = new JTextArea(defaultText);
        textArea.setFont(AppConstants.NORMAL_FONT);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(AppConstants.BUTTON_FONT);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void addField(JPanel panel, GridBagConstraints gbc, JTextField field, int row) {
        gbc.gridy = row;
        panel.add(field, gbc);
    }

    private void analyzePerformance() {
        try {
            currentStudent = readStudentFromForm();
            studentManager.registerStudent(currentStudent);
            currentResult = analyzer.analyze(currentStudent);
            updateResultScreen();
        } catch (ValidationException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Invalid Input", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Invalid Number",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private Student readStudentFromForm() throws ValidationException {
        String studentId = requireText(studentIdField, "Student ID");
        String name = requireText(nameField, "Student name");
        int age = Integer.parseInt(requireText(ageField, "Age"));
        String course = requireText(courseField, "Course");
        double attendance = Double.parseDouble(requireText(attendanceField, "Attendance"));
        double assignment = Double.parseDouble(requireText(assignmentField, "Assignment score"));

        if (age <= 0) {
            throw new ValidationException("Age must be greater than 0.");
        }

        Student student = new Student(studentId, name, age, course);
        student.setAttendancePercentage(attendance);
        student.setAssignmentScore(assignment);

        for (int index = 0; index < subjectFields.size(); index++) {
            String subjectName = subjectFields.get(index).getText().trim();
            int marks = (Integer) markSpinners.get(index).getValue();
            if (!subjectName.isEmpty()) {
                student.addSubjectMark(new SubjectMark(subjectName, marks));
            }
        }

        if (student.getSubjectMarks().isEmpty()) {
            throw new ValidationException("Please enter at least one subject name.");
        }

        return student;
    }

    private String requireText(JTextField field, String fieldName) throws ValidationException {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            throw new ValidationException(fieldName + " is required.");
        }
        return value;
    }

    private void updateResultScreen() {
        totalValueLabel.setText(String.valueOf(currentResult.getTotalMarks()));
        averageValueLabel.setText(String.format("%.2f", currentResult.getAverageMarks()));
        gradeValueLabel.setText(currentResult.getGrade());
        categoryValueLabel.setText(currentResult.getPerformanceCategory());

        StringBuilder suggestions = new StringBuilder();
        for (String suggestion : currentResult.getSuggestions()) {
            suggestions.append("- ").append(suggestion).append("\n");
        }
        suggestionsArea.setText(suggestions.toString());
        reportArea.setText(reportGenerator.buildReport(currentStudent, currentResult));
    }

    private void saveReport() {
        if (currentStudent == null || currentResult == null) {
            JOptionPane.showMessageDialog(this, "Please analyze performance before saving a report.",
                    "Report Not Ready", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            File savedFile = reportGenerator.saveReport(currentStudent, currentResult);
            JOptionPane.showMessageDialog(this, "Report saved successfully:\n" + savedFile.getAbsolutePath(),
                    "Report Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "Unable to save report: " + exception.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSampleData() {
        clearForm();
        studentIdField.setText("S101");
        nameField.setText("Aarav Sharma");
        ageField.setText("18");
        courseField.setText("B.Sc Computer Science");
        attendanceField.setText("72");
        assignmentField.setText("58");

        String[] subjects = {"Java", "Database", "Mathematics", "English", "Networking"};
        int[] marks = {88, 64, 47, 76, 52};
        for (int index = 0; index < subjects.length; index++) {
            subjectFields.get(index).setText(subjects[index]);
            markSpinners.get(index).setValue(marks[index]);
        }
    }

    private void clearForm() {
        studentIdField.setText("");
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
        attendanceField.setText("");
        assignmentField.setText("");

        for (int index = 0; index < subjectFields.size(); index++) {
            subjectFields.get(index).setText("");
            markSpinners.get(index).setValue(0);
        }

        totalValueLabel.setText("0");
        averageValueLabel.setText("0.00");
        gradeValueLabel.setText("-");
        categoryValueLabel.setText("-");
        suggestionsArea.setText("Suggestions will appear here after analysis.");
        reportArea.setText("Generated student report will appear here.");
        currentStudent = null;
        currentResult = null;
    }
}

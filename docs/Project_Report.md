# Student Performance Analyzer - Project Report

## Aim

To develop a Java Swing application that analyzes student academic performance using marks, attendance percentage, and assignment score.

## Introduction

Student Performance Analyzer is a desktop-based Java application. It allows a user to register student details, enter marks for multiple subjects, enter attendance and assignment score, and generate a performance report. The system predicts whether the student's performance is Excellent, Good, Average, or Poor.

## Objectives

- To collect student personal and academic details.
- To calculate total marks and average marks.
- To calculate grade based on average marks.
- To predict performance category.
- To provide improvement suggestions.
- To generate and save a student report.

## Scope

The project is useful for schools, colleges, and beginner academic demonstrations. It can help teachers quickly understand student performance and guide students based on weak areas.

## Technologies Used

- Java
- Java Swing
- Java AWT
- File Handling

## Java Concepts Used

- Classes and Objects
- Inheritance using `Person` and `Student`
- Encapsulation using private fields with getters and setters
- ArrayList for storing multiple subject marks
- Exception Handling using `try-catch` and custom `ValidationException`
- File Handling using `File`, `FileWriter`, and `IOException`
- Packages for proper project structure

## System Modules

### Login Module

The login screen accepts username and password. For demonstration, the default username is `admin` and the default password is `admin`.

### Student Management Module

This module stores student details such as student ID, name, age, and course. The `StudentManager` class keeps student records in an `ArrayList`.

### Marks Management Module

This module accepts subject names and marks. Five subject rows are provided in the GUI, and at least one subject must be entered.

### Performance Analysis Module

The `PerformanceAnalyzer` class calculates:

- Total marks
- Average marks
- Grade
- Performance category
- Improvement suggestions

### Report Generation Module

The `ReportGenerator` class creates a formatted text report and saves it in the `reports` folder.

## Working of Project

1. The user starts the application from `com.spa.Main`.
2. The login screen appears.
3. After successful login, the dashboard opens.
4. The user enters student details, attendance, assignment score, subjects, and marks.
5. The system validates all inputs.
6. The analyzer calculates total, average, grade, and performance category.
7. Suggestions are displayed based on weak areas.
8. The report is shown on screen and can be saved as a text file.

## Performance Rules

- Average `>= 85`: Excellent
- Average `>= 70`: Good
- Average `>= 50`: Average
- Average `< 50`: Poor

## Grade Rules

- Average `>= 85`: A
- Average `>= 70`: B
- Average `>= 50`: C
- Average `>= 35`: D
- Average `< 35`: F

## Suggestion Rules

- Attendance below `75%`: Improve attendance.
- Marks below `50` in any subject: Focus on weak subjects.
- Assignment score below `60%`: Complete assignments regularly.

## Advantages

- Beginner-friendly code structure.
- Easy to explain in viva.
- Uses important Java OOP concepts.
- Provides visual GUI using Swing.
- Generates report files for record keeping.

## Limitations

- Uses a simple fixed login instead of a database login.
- Stores students in memory during program execution.
- Only text reports are generated.
- The GUI currently provides five subject input rows.

## Future Enhancements

- Add JDBC database storage using MySQL or SQLite.
- Add user roles such as admin, teacher, and student.
- Export reports as PDF.
- Add charts for subject-wise performance.
- Add search, update, and delete student records.
- Add more flexible subject entry.

## Conclusion

The Student Performance Analyzer successfully demonstrates how Java, Swing, OOP, exception handling, ArrayList, and file handling can be combined in one academic project. It analyzes student performance clearly and provides useful suggestions for improvement.

## How To Run

Compile:

```bash
javac -d out src/com/spa/Main.java src/com/spa/model/*.java src/com/spa/service/*.java src/com/spa/ui/*.java src/com/spa/util/*.java
```

Run:

```bash
java -cp out com.spa.Main
```

Login:

```text
Username: admin
Password: admin
```

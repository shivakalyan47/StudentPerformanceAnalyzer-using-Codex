# Student Performance Analyzer

A complete beginner-friendly Java Swing project that analyzes student performance from marks, attendance, and assignment score.

## Features

- Login module
- Student registration
- Student details entry
- Multiple subject marks entry
- Attendance and assignment score entry
- Total and average calculation
- Grade calculation
- Performance prediction
- Improvement suggestions
- Student report preview
- Text report saving using file handling

## Package Structure

```text
src/com/spa
  Main.java
  model
  service
  ui
  util
docs
  UML_Class_Diagram.md
  Project_Report.md
reports
```

## How To Compile

```bash
javac -d out src/com/spa/Main.java src/com/spa/model/*.java src/com/spa/service/*.java src/com/spa/ui/*.java src/com/spa/util/*.java
```

## How To Run

```bash
java -cp out com.spa.Main
```

## Login Details

```text
Username: admin
Password: admin
```

## Performance Rules

- Average `>= 85`: Excellent
- Average `>= 70`: Good
- Average `>= 50`: Average
- Average `< 50`: Poor

## Main Classes

- `Person`: Parent class for common person details.
- `Student`: Child class that stores student details.
- `SubjectMark`: Stores one subject and its marks.
- `PerformanceResult`: Stores calculated output.
- `StudentManager`: Handles student records.
- `PerformanceAnalyzer`: Contains calculation and prediction logic.
- `ReportGenerator`: Builds and saves reports.
- `LoginFrame`: Login GUI.
- `DashboardFrame`: Main dashboard GUI.

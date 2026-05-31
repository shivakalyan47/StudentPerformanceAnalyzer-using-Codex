# UML Class Diagram

```mermaid
classDiagram
    class Main {
        +main(String[] args) void
    }

    class Person {
        -String name
        -int age
        +getName() String
        +setName(String name) void
        +getAge() int
        +setAge(int age) void
    }

    class Student {
        -String studentId
        -String course
        -double attendancePercentage
        -double assignmentScore
        -List~SubjectMark~ subjectMarks
        +addSubjectMark(SubjectMark subjectMark) void
        +clearSubjectMarks() void
    }

    class SubjectMark {
        -String subjectName
        -int marks
    }

    class PerformanceResult {
        -int totalMarks
        -double averageMarks
        -String grade
        -String performanceCategory
        -List~String~ suggestions
        +addSuggestion(String suggestion) void
    }

    class StudentManager {
        -List~Student~ students
        +registerStudent(Student student) void
        +getStudents() List~Student~
    }

    class PerformanceAnalyzer {
        +analyze(Student student) PerformanceResult
    }

    class ReportGenerator {
        +buildReport(Student student, PerformanceResult result) String
        +saveReport(Student student, PerformanceResult result) File
    }

    class LoginFrame
    class DashboardFrame
    class ValidationException
    class AppConstants

    Person <|-- Student
    Student "1" o-- "*" SubjectMark
    PerformanceAnalyzer ..> Student
    PerformanceAnalyzer ..> PerformanceResult
    ReportGenerator ..> Student
    ReportGenerator ..> PerformanceResult
    StudentManager o-- "*" Student
    Main ..> LoginFrame
    LoginFrame ..> DashboardFrame
    DashboardFrame ..> StudentManager
    DashboardFrame ..> PerformanceAnalyzer
    DashboardFrame ..> ReportGenerator
```

## Main Relationships

- `Student` inherits common fields from `Person`.
- `Student` contains multiple `SubjectMark` objects using an `ArrayList`.
- `PerformanceAnalyzer` calculates total, average, grade, performance category, and suggestions.
- `ReportGenerator` creates and saves a text report using file handling.
- `DashboardFrame` connects the GUI with student management, analysis, and report generation.

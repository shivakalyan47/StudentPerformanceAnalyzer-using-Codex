package com.spa.service;

import com.spa.model.Student;
import com.spa.util.ValidationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles student registration and keeps student records in memory.
 */
public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void registerStudent(Student student) throws ValidationException {
        if (student == null) {
            throw new ValidationException("Student details cannot be empty.");
        }

        for (int index = 0; index < students.size(); index++) {
            Student existingStudent = students.get(index);
            if (existingStudent.getStudentId().equalsIgnoreCase(student.getStudentId())) {
                students.set(index, student);
                return;
            }
        }

        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }
}

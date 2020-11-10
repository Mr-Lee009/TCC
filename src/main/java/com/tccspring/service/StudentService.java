package com.tccspring.service;

import com.tccspring.model.Student;

import java.util.List;

public interface StudentService {
    public boolean AddStudent(Student x);
    public boolean UpdateStudent(Student x);
    public boolean DeleteStudent(Long id);
    public Student GetStudentById(Long id);
    public List<Student> getAllStudent();
}

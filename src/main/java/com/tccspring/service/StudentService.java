package com.tccspring.service;

import com.tccspring.entity.StudentEntity;

import java.util.List;

public interface StudentService {
    boolean AddStudent(StudentEntity x);
    boolean UpdateStudent(StudentEntity x);
    boolean DeleteStudent(Long id);
    StudentEntity GetStudentById(Long id);
    List<StudentEntity> getAllStudent();
}

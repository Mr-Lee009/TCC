package com.tccspring.service.Impl;

import com.tccspring.entity.StudentEntity;
import com.tccspring.repository.StudentRepository;
import com.tccspring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository repository;

    @Override
    public boolean AddStudent(StudentEntity x) {
        try {
            repository.save(x);
            return  true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean UpdateStudent(StudentEntity x) {
        return false;
    }

    @Override
    public boolean DeleteStudent(Long id) {
        return false;
    }

    @Override
    public StudentEntity GetStudentById(Long id) {
        return null;
    }

    @Override
    public List<StudentEntity> getAllStudent() {
        List<StudentEntity> lst = repository.findAll();
        return lst;
    }
}

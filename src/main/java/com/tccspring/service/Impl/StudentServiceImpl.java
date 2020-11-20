package com.tccspring.service.Impl;

import com.tccspring.entity.StudentEntity;
import com.tccspring.repository.StudentRepository;
import com.tccspring.service.StudentService;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        try {
            Optional<StudentEntity> student = repository.findById(x.getId());
            if(student.isPresent()){
                StudentEntity temp = student.get();
                temp.setFullname(x.getFullname());
                temp.setBirthday(x.getBirthday());
                temp.setPassword(x.getPassword());
                temp.setStudent_code(x.getStudent_code());
                temp.setIs_monitor(x.getIs_monitor());
                temp.setClass_id(x.getClass_id());
                repository.save(temp);
                return true;

            }
            else return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean DeleteStudent(Long id) {
        try{
            repository.deleteById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public StudentEntity GetStudentById(Long id) {
        try{
            StudentEntity x = repository.findById(id).get();
            if(x!=null) return  x;
            else return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<StudentEntity> getAllStudent() {
        List<StudentEntity> lst = repository.findAll();
        return lst;
    }


    @Override
    public boolean ChangePassword(String usercode,String currentPass,String password){
         StudentEntity x = repository.findOneByStudentCodeAndPassword(usercode,currentPass);
         if(x!=null){
            x.setPassword(password);
            repository.save(x);
            return  true;
         }
         return false;
    }
}

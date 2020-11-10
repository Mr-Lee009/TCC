package com.tccspring.service.Impl;

import com.tccspring.model.Student;
import com.tccspring.service.StudentService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private List<Student> studentList = new ArrayList<>();

    @Override
    public boolean AddStudent(Student x) {
        if(x!=null){
            if(GetStudentById(x.getId())== null){
                studentList.add(x);
                return true;
            }
            return false;
        }
        else return false;
    }

    @Override
    public boolean UpdateStudent(Student x) {
        if(!studentList.isEmpty()){
            for (int i = 0;i<studentList.size();i++){
                if(studentList.get(i).getId()==x.getId()){
                    studentList.get(i).setStudent_code(x.getStudent_code());
                    studentList.get(i).setIs_monitor(x.isIs_monitor());
                    studentList.get(i).setPassword(x.getPassword());
                    studentList.get(i).setClass_id(x.getClass_id());
                    studentList.get(i).setFullname(x.getFullname());
                    studentList.get(i).setBirthday(x.getBirthday());
                    return true;
                }
            }
        }
        return  false;
    }

    @Override
    public boolean DeleteStudent(Long id) {

        Student student = GetStudentById(id);
        if(student!=null){
            studentList.remove(student);
            return true;
        }
        return  false;
    }

    @Override
    public Student GetStudentById(Long id) {
        if(!studentList.isEmpty()){
            for (int i = 0;i<studentList.size();i++){
                if(studentList.get(i).getId()==id){
                    return studentList.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public List<Student> getAllStudent() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        formatter.format(date);
        if(studentList.isEmpty()){
            studentList.add(new Student(1L,"15150076","admin","123456",date,1L,true));
            studentList.add(new Student(2L,"15150077","Le Anh Duc","123456",date,1L,false));
            studentList.add(new Student(3L,"15150078","User_1","123456",date,1L,false));
        }
        return this.studentList;
    }
}

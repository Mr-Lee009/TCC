package com.tccspring.controller;

import com.tccspring.entity.StudentEntity;
import com.tccspring.helper.StudentExcel;
import com.tccspring.service.StudentService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/Student",method = RequestMethod.GET)
    public List<StudentEntity> getAlStudent(){
        return  studentService.getAllStudent();
    }
    @RequestMapping(value = "/Student/{id}",method = RequestMethod.GET)
    public StudentEntity getStudentById(@PathVariable(value = "id") Long id){
        StudentEntity student = studentService.GetStudentById(id);
        if(studentService.GetStudentById(id)!=null)
            return student;
        else
            return null;
    }

    @RequestMapping(value = "/Student/{id}",method = RequestMethod.DELETE)
    public String deleteStudent(@PathVariable(value = "id") Long id){
        if(studentService.DeleteStudent(id)==true)
            return "Delete Student Sucess";
        else
            return "Delete Student Fail";
    }

    @RequestMapping(value = "/Student",method = RequestMethod.POST)
    public String addStudent(@RequestBody StudentEntity student){
        if(studentService.AddStudent(student))
            return "Add Sucess";
        else
            return "Add Fail";
    }

    @RequestMapping(value = "/Student",method = RequestMethod.PUT)
    public String updateStudent(@RequestBody StudentEntity student){
        if(studentService.UpdateStudent(student))
            return "Update Sucess";
        else
            return "Update Fail";
    }


    // export excel
    @RequestMapping(value = "/export-excel",method = RequestMethod.GET)
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Danh_sach_sinh_vien_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<StudentEntity> listStudent = studentService.getAllStudent();

        StudentExcel excelExporter = new StudentExcel(listStudent);

        excelExporter.export(response);
    }

    // import data from file excel (fie mau dat trong resources)
    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<StudentEntity>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;
        List<StudentEntity> list = new ArrayList<>();
        StudentExcel excelExporter = new StudentExcel(list);

        list = excelExporter.importExcelFile(files);
        if(list.isEmpty()){
            return null;
        }
        else {
            for (StudentEntity s : list){
                studentService.AddStudent(s);
            }
        }
        return new ResponseEntity<>(studentService.getAllStudent(), status);
    }

    @RequestMapping(value = "/Student/ChangePassword",method = RequestMethod.POST)
    public String changePassword(@RequestBody ChangPass changPass){
        try{
            if(studentService.ChangePassword(changPass.UserCode,changPass.OldPass,changPass.NewPass)){
                return "Change Password Success";
            }
            return "Change Password Fail";
        }catch (Exception e){
            return "Change Password Fail";
        }
    }


}

class ChangPass{
    public String UserCode;
    public String OldPass;
    public String NewPass;
}



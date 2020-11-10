package com.tccspring.controller;

import com.tccspring.Helper.StudentExcelExporter;
import com.tccspring.model.Student;
import com.tccspring.service.StudentService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/Student",method = RequestMethod.GET)
    public List<Student> getAlStudent(){
        return  studentService.getAllStudent();
    }
    @RequestMapping(value = "/Student/{id}",method = RequestMethod.GET)
    public Student getStudentById(@PathVariable(value = "id") Long id){
        Student student = studentService.GetStudentById(id);
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
    public String addStudent(@RequestBody Student student){
        if(studentService.AddStudent(student))
            return "Add Sucess";
        else
            return "Add Fail";
    }

    @RequestMapping(value = "/Student",method = RequestMethod.PUT)
    public String updateStudent(@RequestBody Student student){
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

        List<Student> listStudent = studentService.getAllStudent();

        StudentExcelExporter excelExporter = new StudentExcelExporter(listStudent);

        excelExporter.export(response);
    }

    // import data from file excel
    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<Student>> importExcelFile(@RequestParam("file") MultipartFile files) throws IOException {
        HttpStatus status = HttpStatus.OK;

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Student student = new Student();

                XSSFRow row = worksheet.getRow(index);


                long num = Long.parseLong(row.getCell(0).getStringCellValue());
                student.setId(num);

                student.setStudent_code( row.getCell(1).getStringCellValue());
                student.setFullname( row.getCell(2).getStringCellValue());
                student.setPassword( row.getCell(3).getStringCellValue());

                student.setBirthday(new Date());
                // convert string to date
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
                    Date date = formatter.parse(row.getCell(4).getStringCellValue());
                    student.setBirthday(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long id_class = Long.parseLong(row.getCell(5).getStringCellValue());
                student.setClass_id(Long.parseLong(String.valueOf(id_class)));

                studentService.AddStudent(student);
            }
        }
        return new ResponseEntity<>(studentService.getAllStudent(), status);
    }
}


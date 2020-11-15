package com.tccspring.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "student_code")
    private String student_code;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "password")
    private String password;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "class_id")
    private Long class_id;
    @Column(name = "is_monitor")
    private boolean is_monitor;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public boolean isIs_monitor() {
        return is_monitor;
    }

    public void setIs_monitor(boolean is_monitor) {
        this.is_monitor = is_monitor;
    }

    public StudentEntity(){}

    public StudentEntity(Long id, String student_code, String fullname, String password, Date birthday, Long class_id, boolean is_monitor) {
        this.id = id;
        this.student_code = student_code;
        this.fullname = fullname;
        this.password = password;
        this.birthday = birthday;
        this.class_id = class_id;
        this.is_monitor = is_monitor;
    }
}

package com.tccspring.repository;

import com.tccspring.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Long> {

    @Query(value = "select s from student s where s.student_code = ?1 and s.password = ?2",nativeQuery = true)
    StudentEntity findOneByStudentCodeAndPassword(String student_code, String password);
}

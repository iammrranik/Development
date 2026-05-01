package com.example.database.repository;

import com.example.database.domain.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    Student save(Student student);
    Optional<Student> findById(Integer id);
    List<Student> findAll(int page, int size);
    int count();
    int update(Student student);
    int delete(int id);
}

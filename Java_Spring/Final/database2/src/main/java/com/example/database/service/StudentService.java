package com.example.database.service;

import com.example.database.domain.Student;
import com.example.database.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll(int page, int size) {
        return studentRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return studentRepository.count();
    }

    @Override
    public int update(Student student) {
        return studentRepository.update(student);
    }

    @Override
    public int delete(int id) {
        return studentRepository.delete(id);
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }
}

package com.example.database.api;

import com.example.database.domain.Student;
import com.example.database.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentApi {
    private final StudentService studentService;
    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public void save(@RequestBody Student student) {
        studentService.save(student);
    }

    @PutMapping("/student")
    public void update(@RequestBody Student student) {
        studentService.update(student);
    }

    @DeleteMapping("/student/{id}")
    public void delete(@PathVariable int id) {
        studentService.delete(id);
    }

    @GetMapping("/student/{page}/{size}")
    public List<Student> findAll(@PathVariable int page, @PathVariable int size) {
        return studentService.findAll(page, size);
    }

    @GetMapping("/student/{id}")
    public Optional<Student> findOne(@PathVariable int id) {
        return studentService.findById(id);
    }



}

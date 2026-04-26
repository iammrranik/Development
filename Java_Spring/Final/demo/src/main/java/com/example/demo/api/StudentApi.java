package com.example.demo.api;

import com.example.demo.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentApi {

    ArrayList<Student> students = new ArrayList<>();

    public StudentApi() {
        students.add(new Student(1, "A", 3.5f));
        students.add(new Student(2, "B", 3.2f));
        students.add(new Student(3, "C", 3.8f));
        students.add(new Student(4, "D", 2.9f));
        students.add(new Student(5, "E", 3.6f));
        students.add(new Student(6, "F", 3.1f));
        students.add(new Student(7, "G", 3.9f));
        students.add(new Student(8, "H", 2.7f));
        students.add(new Student(9, "I", 3.4f));
        students.add(new Student(10, "J", 3.0f));
    }

    @GetMapping("/api/student")
    public ArrayList<Student> getStudent() {
        System.out.println("Get All Student");
        return students;
    }

    @GetMapping("/api/student/{id}")
    public Student getStudent(@PathVariable Integer id) {
        System.out.println("Getting student with id: " + id);
        return students.get(id-1);
    }

    @PostMapping("/api/student")
    public Student addStudent(@RequestBody Student student) {
        System.out.println("Adding student: " + student);
        students.add(student);
        return student;
    }

    @DeleteMapping("/api/student/{id}")
    public String removeStudent(@PathVariable Integer id) {
        System.out.println("Deleting student with id: " + id);
        students.remove(id-1);
        return "Deleted student with id: " + id;
    }

    @PutMapping("/api/student/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        if (id <= 0 || id > students.size()) {
            return null;
        }

        student.setId(id); // enforce correct ID
        students.set(id - 1, student);

        return student;
    }

}

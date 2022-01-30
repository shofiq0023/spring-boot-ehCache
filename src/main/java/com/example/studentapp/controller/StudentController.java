package com.example.studentapp.controller;

import com.example.studentapp.model.Student;
import com.example.studentapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/getAll")
    public List<Student> getAllStudent() {
        return service.getAllStudent();
    }

    @GetMapping("/get/{id}")
    public Optional<Student> getStudent(@PathVariable("id") Long id) {
        return service.getStudent(id);
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        service.addStudent(student);
    }

    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        service.deleteStudent(id);
    }
}

package com.example.studentapp.services;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository repo;

    public void addStudent(Student student) {
        logger.info("[POST] - Adding new student in db");
        repo.save(student);
    }

    public List<Student> getAllStudent() {
        List<Student> students = new ArrayList<>();
        repo.findAll().forEach(students::add);
        logger.info("[GET] - Getting all student from db");

        return students;
    }

    @Cacheable(cacheNames = "students", key = "#id")
    public Optional<Student> getStudent(Long id) {
        logger.info("[GET] - Getting single student from db");
        return repo.findById(id);
    }

    @CachePut(cacheNames = "students", key = "#id")
    public Student updateStudent(Long id, Student student) {
        if(repo.existsById(id)) {
            repo.save(student);
            logger.info("[PUT] - Updating student in db");

            return student;
        } else {
            logger.info("[PUT] - NULL");
            return null;
        }
    }

    @CacheEvict(cacheNames = "students", key = "#id")
    public void deleteStudent(Long id) {
        logger.info("[DELETE] - Deleting student from db");
        repo.deleteById(id);
    }
}

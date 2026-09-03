package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.entity.User; 
import com.example.demo.repository.UserRepository; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class UserController { 

    private final UserRepository userRepository; 

   
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User addStudent(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAllStudents() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getSingleStudent(@PathVariable long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with ID: " + id + " not found!!"));
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        boolean existing = userRepository.existsById(id);
        if (!existing) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with ID: " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}

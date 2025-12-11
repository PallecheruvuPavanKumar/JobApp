package com.example.JobApp.Controller;

import com.example.JobApp.Model.User;
import com.example.JobApp.service.MyUserDetailsService;
import com.example.JobApp.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {
    
    @Autowired
    private UserRegistrationService userRegistrationService;
    
    @PostMapping("/register")
    public ResponseEntity<User> newUserRegistration(@RequestBody User user) {
        User savedUser = userRegistrationService.createUser(user);
        System.out.println("created");
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}

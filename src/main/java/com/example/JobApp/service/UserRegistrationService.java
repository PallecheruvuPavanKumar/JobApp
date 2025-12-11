package com.example.JobApp.service;

import com.example.JobApp.Model.User;
import com.example.JobApp.repositary.UserRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    
    @Autowired
    private UserRegistrationRepo userRegistrationRepo;
    
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    
    public User createUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRegistrationRepo.save(user);
        User byusername = userRegistrationRepo.findByusername(user.getUsername());
        return byusername;
    }
}

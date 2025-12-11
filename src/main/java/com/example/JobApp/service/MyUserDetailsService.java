package com.example.JobApp.service;

import com.example.JobApp.Model.User;
import com.example.JobApp.Model.UserPrincipal;
import com.example.JobApp.repositary.UserRegistrationRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRegistrationRepo userRegistrationRepo;
    
    @Override
    public UserDetails loadUserByUsername(String userName){
        System.out.println("Entered");
        User byUserName = userRegistrationRepo.findByusername(userName);
        if (byUserName == null) {
            System.out.println("user is null");
            throw new UsernameNotFoundException("User not found: " + byUserName);
        }
        return new UserPrincipal(byUserName);
    }
}

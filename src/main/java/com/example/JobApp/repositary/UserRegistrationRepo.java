package com.example.JobApp.repositary;

import com.example.JobApp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepo extends JpaRepository<User,Integer> {
    
    User findByusername(String userName);
}

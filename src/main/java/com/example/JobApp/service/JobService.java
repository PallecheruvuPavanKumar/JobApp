package com.example.JobApp.service;

import com.example.JobApp.Model.JobPost;
import com.example.JobApp.repositary.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    
    @Autowired
    private JobRepo jobRepo;
    
    public List<JobPost> getAllJobs(){
        return jobRepo.getAllJobs();
    }
    
    public void addJob(JobPost jobPost){
        jobRepo.addJob(jobPost);
    }
    
    public JobPost getRequiredJob(int postId){
        return jobRepo.getRequiredJob(postId);
    }
    
    public void updateJob(JobPost jobPost) {
         jobRepo.updateJob(jobPost);
    }
    
    public void deleteJob(int postId) {
        jobRepo.deleteJob(postId);
    }
}

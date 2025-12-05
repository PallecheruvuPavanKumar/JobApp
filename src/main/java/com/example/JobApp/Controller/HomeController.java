package com.example.JobApp.Controller;

import com.example.JobApp.Model.JobPost;
import com.example.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HomeController {
    
    @Autowired
    private JobService service;
    
    @GetMapping({"/","home"})
    public String home(){
        return "home";
    }
    
    @GetMapping(path = "/jobPosts",produces = {"application/json"})
    public List<JobPost> viewAllJobs() {
        return service.getAllJobs();
    }
    
    @RequestMapping("/addjob")
    public ModelAndView addJob(ModelAndView modelAndView){
        modelAndView.setViewName("addJob");
        return modelAndView;
    }
    
    @PostMapping("/handleForm")
    public String handleForm(JobPost jobPost) {
        service.addJob(jobPost);
        return "success";
    }
    
    @GetMapping("/jobPost/{postId}")
    public JobPost getRequiredJob(@PathVariable("postId") int postId) {
        return service.getRequiredJob(postId);
    }
    
    @PostMapping("/jobPost")
    public void addJob(@RequestBody JobPost jobPost){
        service.addJob(jobPost);
    }
    
    @PutMapping("/jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getRequiredJob(jobPost.getPostId());
    }
    
    @DeleteMapping("/jobPost/{postId}")
    public void deleteJob(@PathVariable int postId){
        service.deleteJob(postId);
    }
    
    @GetMapping("load")
    public String loadData() {
        service.load();
        return "success";
    }
    
    @GetMapping("/jobPosts/keyword/{keyword}")
    public List<JobPost> findByPostDesc(@PathVariable("keyword") String keyword){
        return service.getNameUsingPostDesc(keyword,keyword);
    }
}

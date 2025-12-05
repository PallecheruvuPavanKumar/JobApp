package com.example.JobApp.repositary;

import com.example.JobApp.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JobRepo extends JpaRepository<JobPost,Integer> {

    List<JobPost> findByPostProfileContainingOrPostDesc(String postProfile,String postDesc);
}

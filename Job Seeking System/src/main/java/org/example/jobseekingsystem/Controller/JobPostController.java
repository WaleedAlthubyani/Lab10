package org.example.jobseekingsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.ApiResponse.ApiResponse;
import org.example.jobseekingsystem.Model.JobPost;
import org.example.jobseekingsystem.Service.JobPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/job-seeking-system/job-post")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<JobPost>>> getAllJobPosts(){
        return ResponseEntity.status(200).body(new ApiResponse<>(jobPostService.getAllJobPosts()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(201).body(new ApiResponse<>("Job post created successfully"));
    }

    @PutMapping("/update/{job_post_id}")
    public ResponseEntity<ApiResponse<String>> updateJobPost(@PathVariable Integer job_post_id, @RequestBody JobPost jobPost, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse<>(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }

        boolean isUpdated= jobPostService.updateJobPost(job_post_id,jobPost);

        if (isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse<>("Job post updated successfully"));

        return ResponseEntity.status(404).body(new ApiResponse<>("Job post not found"));
    }

    @DeleteMapping("/delete/{job_post_id}")
    public ResponseEntity<ApiResponse<String>> deleteJobPost(@PathVariable Integer job_post_id){
        boolean isDeleted= jobPostService.deleteJobPost(job_post_id);

        if (isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse<>("Job post deleted successfully"));

        return ResponseEntity.status(404).body(new ApiResponse<>("Job post not found"));
    }
}

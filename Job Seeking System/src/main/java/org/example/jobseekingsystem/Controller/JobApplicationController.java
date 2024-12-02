package org.example.jobseekingsystem.Controller;

import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.ApiResponse.ApiResponse;
import org.example.jobseekingsystem.Model.JobApplication;
import org.example.jobseekingsystem.Service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-seeking-system/job-application")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<JobApplication>>> getAllJobApplications(){
        return ResponseEntity.status(200).body(new ApiResponse<>(jobApplicationService.getAllJobApplication()));
    }

    @PostMapping("/apply-for-job/{user_id}/{job_post_id}")
    public ResponseEntity<ApiResponse<String>> applyForJob(@PathVariable Integer user_id, @PathVariable Integer job_post_id){
        int result = jobApplicationService.applyForJob(user_id,job_post_id);

        return switch (result) {
            case 0 -> ResponseEntity.status(404).body(new ApiResponse<>("user not found"));
            case 1 -> ResponseEntity.status(404).body(new ApiResponse<>("Job post not found"));
            default -> ResponseEntity.status(201).body(new ApiResponse<>("User applied to job successfully"));
        };
    }

    @DeleteMapping("/withdraw-job-application/{application_id}/{requester_user_id}")
    public ResponseEntity<ApiResponse<String>> withdrawJobApplication(@PathVariable Integer application_id, @PathVariable Integer requester_user_id){
        int result = jobApplicationService.withdrawJobApplication(application_id,requester_user_id);

        return switch (result) {
            case 0 -> ResponseEntity.status(404).body(new ApiResponse<>("Application not found"));
            case 1 -> ResponseEntity.status(401).body(new ApiResponse<>("You can't withdraw someone else's application"));
            default -> ResponseEntity.status(200).body(new ApiResponse<>("Application withdrawn successfully"));
        };
    }

}

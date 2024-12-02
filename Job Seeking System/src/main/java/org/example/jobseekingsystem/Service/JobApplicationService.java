package org.example.jobseekingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.Model.JobApplication;
import org.example.jobseekingsystem.Model.JobPost;
import org.example.jobseekingsystem.Model.User;
import org.example.jobseekingsystem.Repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostService jobPostService;
    private final UserService userService;

    public List<JobApplication> getAllJobApplication(){
        return jobApplicationRepository.findAll();
    }

    public int applyForJob(Integer user_id, Integer job_post_id){
        User user=null;

        for (User u: userService.getAllUsers()){
            if (u.getId().equals(user_id)){
                user=u;
                break;
            }
        }

        if (user==null)
            return 0;//fail user not found

        JobPost jobPost = null;

        for (JobPost j: jobPostService.getAllJobPosts()){
            if (j.getId().equals(job_post_id)){
                jobPost=j;
                break;
            }
        }

        if (jobPost==null)
            return 1; //fail jobPost not found

        JobApplication jobApplication=new JobApplication();

        jobApplication.setUserId(user_id);
        jobApplication.setJobPostId(job_post_id);
        jobApplicationRepository.save(jobApplication);
        return 2;
    }

    public int withdrawJobApplication(Integer application_id,Integer requester_user_id){
        JobApplication jobApplication=null;

        for (JobApplication j:getAllJobApplication()){
            if (j.getId().equals(application_id)){
                jobApplication=j;
                break;
            }
        }

        if (jobApplication==null)
            return 0;//fail application not found

        User user = null;

        for (User u: userService.getAllUsers()){
            if (u.getId().equals(requester_user_id)){
                user=u;
            }
        }

        if (user==null)
            return 1;//fail user not found

        if (!jobApplication.getUserId().equals(requester_user_id))
            return 2;//fail only job applicant can withdraw his application

        jobApplicationRepository.delete(jobApplication);
        return 3;
    }
}

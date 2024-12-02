package org.example.jobseekingsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.jobseekingsystem.Model.JobPost;
import org.example.jobseekingsystem.Repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPosts(){
        return jobPostRepository.findAll();
    }

    public void addJobPost(JobPost job_post){
        jobPostRepository.save(job_post);
    }

    public Boolean updateJobPost(Integer job_post_id, JobPost job_post){
        JobPost oldJobPost=null;

        for (JobPost j:getAllJobPosts()){
            if (j.getId().equals(job_post_id)){
                oldJobPost=j;
                break;
            }
        }

        if (oldJobPost==null)
            return false;

        oldJobPost.setTitle(job_post.getTitle());
        oldJobPost.setDescription(job_post.getDescription());
        oldJobPost.setLocation(job_post.getLocation());
        oldJobPost.setSalary(job_post.getSalary());

        jobPostRepository.save(oldJobPost);

        return true;
    }

    public Boolean deleteJobPost(Integer job_post_id){
        JobPost jobPost=null;

        for (JobPost j:getAllJobPosts()){
            if (j.getId().equals(job_post_id)){
                jobPost=j;
                break;
            }
        }

        if (jobPost==null)
            return false;

        jobPostRepository.delete(jobPost);
        return true;
    }
}

package org.example.jobseekingsystem.Repository;

import org.example.jobseekingsystem.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost,Integer> {
}

package com.jobapp.jabapp.job.imp;

import com.jobapp.jabapp.job.Job;
import com.jobapp.jabapp.job.JobRepository;
import com.jobapp.jabapp.job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceimp implements JobService {


    private JobRepository jobRepository;

    public JobServiceimp(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private long id=0;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createjob(Job job) {
         job.setId(++id);
         jobRepository.save(job);
    }

    @Override
    public Job getjobbyid(long id) {
        return  jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deletejobbyid(long id) {
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public boolean updatejob(long id, Job updatedjob) {
        Optional<Job>jobOptional = jobRepository.findById(id);


            if (jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(updatedjob.getTitle());
                job.setDescription(updatedjob.getDescription());
                job.setMaxSalary(updatedjob.getMaxSalary());
                job.setMinSalary(updatedjob.getMinSalary());
                job.setLocation(updatedjob.getLocation());
                jobRepository.save(job);
                return  true;
            }

        return false;
    }
}

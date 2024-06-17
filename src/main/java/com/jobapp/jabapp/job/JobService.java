package com.jobapp.jabapp.job;

import java.util.List;

public interface JobService {
    List<Job>findAll();
    void createjob(Job job);

    Job getjobbyid(long id);

    boolean deletejobbyid(long id);

    boolean updatejob(long id, Job updatedjob);
}

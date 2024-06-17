package com.jobapp.jabapp.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobapp.jabapp.job.Job;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String discription;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;
//    private List<Reviws>revies


    public Company() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}

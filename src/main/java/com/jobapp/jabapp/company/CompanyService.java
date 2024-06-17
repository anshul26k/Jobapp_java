package com.jobapp.jabapp.company;

import com.jobapp.jabapp.job.Job;

import java.util.List;

public interface CompanyService {
     List<Company>getAllCompanies();
    boolean updatecompany(long id, Company updatedcompany);
    void createcompnay(Company company);
    boolean deletecompany(long id);
    Company getcompanybyid(long id);
}

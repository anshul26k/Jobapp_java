package com.jobapp.jabapp.company.imp;

import com.jobapp.jabapp.company.Company;
import com.jobapp.jabapp.company.CompanyRepository;
import com.jobapp.jabapp.company.CompanyService;
import com.jobapp.jabapp.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceimp implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceimp(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updatecompany(long id, Company updatedcompany) {
        Optional<Company>companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updatedcompany.getName());
            company.setDiscription(updatedcompany.getDiscription());
            company.setJobs(updatedcompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createcompnay(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deletecompany(long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else{
            return  false;
        }

    }

    @Override
    public Company getcompanybyid(long id) {
        return companyRepository.findById(id).orElse(null);
    }


}

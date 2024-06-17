package com.jobapp.jabapp.company;

import com.jobapp.jabapp.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getALlCompanies(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getcompanybyyid(@PathVariable long id){
        Company company = companyService.getcompanybyid(id);
        if(company != null)
            return  new ResponseEntity<>(company, HttpStatus.OK);

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String>createcompany(@RequestBody Company company){
          companyService.createcompnay(company);
          return  ResponseEntity.ok("added succefuly");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatecompany(@PathVariable Long id, @RequestBody Company company){

        boolean updated =  companyService.updatecompany(id,company);
        if(updated)
            return  ResponseEntity.ok("suceefuly updated");

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecompany(@PathVariable Long id){
        boolean deleted =  companyService.deletecompany(id);
        if (deleted)
            return ResponseEntity.ok("deleted successfully");

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

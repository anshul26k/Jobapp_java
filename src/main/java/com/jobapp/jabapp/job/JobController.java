package com.jobapp.jabapp.job;

import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createjob(@RequestBody Job job){
        jobService.createjob(job);
        return ResponseEntity.ok("job added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getjobbyid(@PathVariable long id){
        Job job = jobService.getjobbyid(id);
        if(job != null)
            return  new ResponseEntity<>(job, HttpStatus.OK);

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletejobbyid(@PathVariable long id){

           boolean deleted =  jobService.deletejobbyid(id);
           if (deleted)
               return ResponseEntity.ok("deleted successfully");

           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<String> updatejob(@PathVariable long id,@RequestBody Job updatedjob){
               boolean updated = jobService.updatejob(id,updatedjob);
               if(updated)
                   return ResponseEntity.ok("Updated successfully");

               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

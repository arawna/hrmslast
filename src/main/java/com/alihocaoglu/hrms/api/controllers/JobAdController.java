package com.alihocaoglu.hrms.api.controllers;

import com.alihocaoglu.hrms.busines.abstracts.JobAdService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.JobAd;
import com.alihocaoglu.hrms.entities.dtos.JobAdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobAd")
public class JobAdController {

    private JobAdService jobAdService;

    @Autowired
    public JobAdController(JobAdService jobAdService) {
        this.jobAdService = jobAdService;
    }

    @GetMapping("/getall")
    public DataResult<List<JobAd>> getAll(){
        return this.jobAdService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JobAdDto jobAdDto){
        Result result=this.jobAdService.create(jobAdDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/setPassive")
    public ResponseEntity<?> setPasssive(@RequestParam int jobAdId){
        Result result=this.jobAdService.setPasssive(jobAdId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/setActive")
    public ResponseEntity<?> setActive(@RequestParam int jobAdId){
        Result result=this.jobAdService.setActive(jobAdId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getActiveAds")
    public DataResult<List<JobAd>> getActiveAds(){
        return this.jobAdService.getActiveAds();
    }

    @GetMapping("/getActivesOrderLastDate")
    public DataResult<List<JobAd>> getActivAndOrderLastDate(){
        return this.jobAdService.getActiveAndOrderLastDate();
    }

    @GetMapping("/getActiveAndCompanyId")
    public DataResult<List<JobAd>> getActiveAndCompanyId(@RequestParam int companyId){
        return this.jobAdService.getActiveAndCompanyId(companyId);
    }
}

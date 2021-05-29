package com.alihocaoglu.hrms.api.controllers;

import com.alihocaoglu.hrms.busines.abstracts.JobAdService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.core.utilities.results.SuccessDataResult;
import com.alihocaoglu.hrms.entities.concretes.JobAd;
import com.alihocaoglu.hrms.entities.concretes.JobAdDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result create(@RequestBody JobAdDto jobAdDto){
        return this.jobAdService.create(jobAdDto);
    }

    @GetMapping("/setPassive")
    public Result setPasssive(@RequestParam int jobAdId){
        return this.jobAdService.setPasssive(jobAdId);
    }

    @GetMapping("/setActive")
    public Result setActive(@RequestParam int jobAdId){
        return this.jobAdService.setActive(jobAdId);
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

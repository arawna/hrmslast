package com.alihocaoglu.hrms.api.controllers;

import com.alihocaoglu.hrms.busines.abstracts.JobAdService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.ErrorDataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.core.utilities.results.SuccessDataResult;
import com.alihocaoglu.hrms.entities.concretes.JobAd;
import com.alihocaoglu.hrms.entities.dtos.JobAdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobAd")
@CrossOrigin
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

    @GetMapping("/getByJobAdId")
    public DataResult<JobAd> getByJobAdId(@RequestParam int id){
        // Bunların normalde busineste yazılması gerek ama retun işleminde json kaynaklı bir referans adresi hatası alıyorum
        // Tek bulabildiğim çözüm yoluda bu oldu
        // jobAd ye direk eşittir diyip jobserviceden gelen referansı verdim hata çzülmedi
        // Son çare olarak burada new leyip parametrelerini eşitledim
        JobAd jobAd=new JobAd();
        JobAd jobAdForSet=this.jobAdService.getByJobAdId(id).getData();
        if(jobAdForSet==null){
            return new ErrorDataResult<JobAd>("Böyle bir ilan yok");
        }
        jobAd.setId(jobAdForSet.getId());
        jobAd.setEmployer(jobAdForSet.getEmployer());
        jobAd.setJobPosition(jobAdForSet.getJobPosition());
        jobAd.setDescription(jobAdForSet.getDescription());
        jobAd.setCity(jobAdForSet.getCity());
        jobAd.setMaxSalary(jobAdForSet.getMinSalary());
        jobAd.setMaxSalary(jobAdForSet.getMaxSalary());
        jobAd.setOpenPositions(jobAdForSet.getOpenPositions());
        jobAd.setLastDate(jobAdForSet.getLastDate());
        jobAd.setActive(jobAdForSet.isActive());
        jobAd.setCreateDate(jobAdForSet.getCreateDate());

        return new SuccessDataResult<JobAd>(jobAd,"Data listelendi");
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody JobAdDto jobAdDto){
        Result result=this.jobAdService.create(jobAdDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/setPassive")
    public ResponseEntity<?> setPasssive(@RequestParam int jobAdId){
        Result result=this.jobAdService.setPasssive(jobAdId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/setActive")
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

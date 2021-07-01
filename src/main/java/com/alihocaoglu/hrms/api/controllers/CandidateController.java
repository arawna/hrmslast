package com.alihocaoglu.hrms.api.controllers;

import com.alihocaoglu.hrms.busines.abstracts.CandidateService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Candidate;
import com.alihocaoglu.hrms.entities.dtos.CandidateForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidateController {

    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/getall")
    public DataResult<List<Candidate>> getAll(){
        return candidateService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CandidateForRegisterDto candidateForRegisterDto){
        Result result=this.candidateService.add(candidateForRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getMailVerifyTrue")
    DataResult<List<Candidate>> getMailVerifyTrue(){
        return this.candidateService.getMailVerifyTrue();
    }
}

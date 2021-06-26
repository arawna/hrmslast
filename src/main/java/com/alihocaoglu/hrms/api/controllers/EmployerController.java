package com.alihocaoglu.hrms.api.controllers;

import com.alihocaoglu.hrms.busines.abstracts.EmployerService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Employer;
import com.alihocaoglu.hrms.entities.concretes.EmployerUpdate;
import com.alihocaoglu.hrms.entities.dtos.EmployerForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
@CrossOrigin
public class EmployerController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll(){
        return this.employerService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody EmployerForRegisterDto employerForRegisterDto){
        Result result=this.employerService.add(employerForRegisterDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getById")
    DataResult<Employer> getById(@RequestParam int id){
        return this.employerService.getById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody EmployerUpdate employerUpdate){
        Result result = this.employerService.update(employerUpdate);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/verifyUpdate")
    public ResponseEntity<?> verifyUpdate(@RequestParam int employerUpdateId,@RequestParam int staffId){
        Result result = this.employerService.verifyUpdate(employerUpdateId,staffId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}

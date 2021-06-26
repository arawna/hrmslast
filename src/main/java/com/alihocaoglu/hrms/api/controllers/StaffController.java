package com.alihocaoglu.hrms.api.controllers;

import com.alihocaoglu.hrms.busines.abstracts.StaffService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Staff;
import com.alihocaoglu.hrms.entities.dtos.StaffUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/getall")
    public DataResult<List<Staff>> getAll(){
        return this.staffService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Staff staff){
        Result result=this.staffService.create(staff);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(StaffUpdateDto staffUpdateDto){
        Result result=this.staffService.update(staffUpdateDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}

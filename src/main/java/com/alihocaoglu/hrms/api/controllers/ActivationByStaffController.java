package com.alihocaoglu.hrms.api.controllers;


import com.alihocaoglu.hrms.busines.abstracts.ActivationByStaffService;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acticationbystaff")
public class ActivationByStaffController {

    private ActivationByStaffService activationByStaffService;

    @Autowired
    public ActivationByStaffController(ActivationByStaffService activationByStaffService) {
        this.activationByStaffService = activationByStaffService;
    }

    @GetMapping("/activateemployer")
    public Result activateEmployer(@RequestParam int employerId,@RequestParam int staffId){
        return activationByStaffService.activateEmployer(employerId,staffId);
    }
}

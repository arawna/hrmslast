package com.alihocaoglu.hrms.api.controllers;


import com.alihocaoglu.hrms.busines.abstracts.ActivationByStaffService;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acticationbystaff")
public class ActivationByStaffController {

    private ActivationByStaffService activationByStaffService;

    @Autowired
    public ActivationByStaffController(ActivationByStaffService activationByStaffService) {
        this.activationByStaffService = activationByStaffService;
    }

    @GetMapping("/activateemployer/{id}")
    public Result activateEmployer(@PathVariable int id){
        return activationByStaffService.activateEmployer(id);
    }
}

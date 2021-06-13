package com.alihocaoglu.hrms.api.controllers;

import com.alihocaoglu.hrms.busines.abstracts.CityService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
@CrossOrigin
public class CitysController {

    private CityService cityService;

    @Autowired
    public CitysController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getAll")
    public DataResult<List<City>> getAll(){
        return this.cityService.getAll();
    }
}

package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.entities.concretes.City;

import java.util.List;

public interface CityService {
    public DataResult<List<City>> getAll();
}

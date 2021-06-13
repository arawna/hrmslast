package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.CityService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.SuccessDataResult;
import com.alihocaoglu.hrms.dataAccess.abstracts.CityDao;
import com.alihocaoglu.hrms.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll(),"Data listelendi");
    }
}

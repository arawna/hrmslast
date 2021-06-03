package com.alihocaoglu.hrms.busines.abstracts;


import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Experiance;
import com.alihocaoglu.hrms.entities.dtos.ExperianceForSetDto;

import java.util.List;

public interface ExperianceService{
    Result add(ExperianceForSetDto experianceForSetDto);
    Result delete(int experianceId);
    DataResult<List<Experiance>> getByCvId(int id);
}

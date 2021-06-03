package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.School;
import com.alihocaoglu.hrms.entities.dtos.SchoolForSerDto;

import java.util.List;

public interface SchoolService {
    public Result addSchool(SchoolForSerDto schoolForSerDto);
    public Result deleteSchool(int schoolId);
    public DataResult<List<School>> getByCvId(int cvId);
}

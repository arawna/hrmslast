package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Employer;
import com.alihocaoglu.hrms.entities.concretes.EmployerUpdate;
import com.alihocaoglu.hrms.entities.dtos.EmployerForRegisterDto;

import java.util.List;

public interface EmployerService {
    DataResult<List<Employer>> getAll();
    DataResult<Employer> getByEmail(String email);
    Result add(EmployerForRegisterDto employerDto);
    DataResult<Employer> getById(int id);
    Result update(EmployerUpdate employerUpdate);
    Result verifyUpdate(int employerUpdateId,int staffId);
}

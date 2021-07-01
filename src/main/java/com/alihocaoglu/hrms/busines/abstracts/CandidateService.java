package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Candidate;
import com.alihocaoglu.hrms.entities.dtos.CandidateForRegisterDto;

import java.util.List;

public interface CandidateService {
    DataResult<List<Candidate>> getAll();
    DataResult<Candidate> getByNationalNumber(String nationalNumber);
    DataResult<Candidate> getByEmail(String email);
    Result add(CandidateForRegisterDto candidateForRegisterDto);
    DataResult<List<Candidate>> getMailVerifyTrue();
}

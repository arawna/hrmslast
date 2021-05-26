package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.entities.concretes.Candidate;

public interface NationalValidationService {
    boolean validate(Candidate candidate);
}

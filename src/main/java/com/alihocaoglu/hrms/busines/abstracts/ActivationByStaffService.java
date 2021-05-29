package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Employer;

public interface ActivationByStaffService {
    void createActivationByStaff(Employer employer);
    Result activateEmployer(int employerId,int staffId);
}

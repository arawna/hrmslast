package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Staff;

import java.util.List;

public interface StaffService {
    public Result create(Staff staff);
    public DataResult<List<Staff>> getAll();
}

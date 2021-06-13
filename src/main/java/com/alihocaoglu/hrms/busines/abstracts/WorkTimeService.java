package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.entities.concretes.WorkTime;

import java.util.List;

public interface WorkTimeService {
    public DataResult<List<WorkTime>> getAll();
}

package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.entities.concretes.WorkPlace;

import java.util.List;

public interface WorkPlaceService {
    public DataResult<List<WorkPlace>> getAll();
}

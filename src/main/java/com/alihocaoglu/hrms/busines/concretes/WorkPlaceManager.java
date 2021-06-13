package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.WorkPlaceService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.SuccessDataResult;
import com.alihocaoglu.hrms.dataAccess.abstracts.WorkPlaceDao;
import com.alihocaoglu.hrms.entities.concretes.WorkPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkPlaceManager implements WorkPlaceService {

    private WorkPlaceDao workPlaceDao;

    @Autowired
    public WorkPlaceManager(WorkPlaceDao workPlaceDao) {
        this.workPlaceDao = workPlaceDao;
    }

    @Override
    public DataResult<List<WorkPlace>> getAll() {
        return new SuccessDataResult<List<WorkPlace>>(this.workPlaceDao.findAll(),"Data listelendi");
    }
}

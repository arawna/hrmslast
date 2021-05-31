package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.JobPositionService;
import com.alihocaoglu.hrms.core.utilities.results.*;
import com.alihocaoglu.hrms.dataAccess.abstracts.JobPositionDao;
import com.alihocaoglu.hrms.entities.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {

    private JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(),"Data listelendi");
    }

    @Override
    public Result add(JobPosition jobPosition) {
        if(getByName(jobPosition.getName()).getData() != null){
            return new ErrorResult("Bu isimde bir pozisyon zaten kayıtlı");
        }else if(jobPosition.getName().length() <=2){
            return new ErrorResult("İş ismi 2 karakterden kısa olamaz");
        }else{
            this.jobPositionDao.save(jobPosition);
            return new SuccessResult("İş eklendi");
        }


    }

    @Override
    public DataResult<JobPosition> getByName(String name) {
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.findByName(name),"Listelendi");
    }
}

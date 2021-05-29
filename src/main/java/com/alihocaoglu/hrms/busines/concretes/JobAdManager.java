package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.JobAdService;
import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.core.utilities.results.SuccessDataResult;
import com.alihocaoglu.hrms.core.utilities.results.SuccessResult;
import com.alihocaoglu.hrms.dataAccess.abstracts.CityDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.EmployerDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.JobAdDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.JobPositionDao;
import com.alihocaoglu.hrms.entities.concretes.JobAd;
import com.alihocaoglu.hrms.entities.concretes.JobAdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobAdManager implements JobAdService {

    private JobAdDao jobAdDao;
    private JobPositionDao jobPositionDao;
    private EmployerDao employerDao;
    private CityDao cityDao;

    @Autowired
    public JobAdManager(JobAdDao jobAdDao,JobPositionDao jobPositionDao,EmployerDao employerDao,CityDao cityDao) {
        this.jobAdDao = jobAdDao;
        this.jobPositionDao=jobPositionDao;
        this.employerDao=employerDao;
        this.cityDao=cityDao;
    }

    @Override
    public Result create(JobAdDto jobAdDto) {

        JobAd jobAd=new JobAd();
        jobAd.setId(0);
        jobAd.setJobPosition(this.jobPositionDao.getById(jobAdDto.getJobPositionId()));
        jobAd.setEmployer(this.employerDao.getById(jobAdDto.getEmployerId()));
        jobAd.setDescription(jobAdDto.getDescription());
        jobAd.setCity(this.cityDao.getById(jobAdDto.getCityId()));
        jobAd.setMinSalary(jobAdDto.getMinSalary());
        jobAd.setMaxSalary(jobAdDto.getMaxSalary());
        jobAd.setOpenPositions(jobAdDto.getOpenPositions());
        jobAd.setLastDate(jobAdDto.getLastDate());
        jobAd.setActive(true);
        jobAd.setCreateDate(LocalDate.now());


        this.jobAdDao.save(jobAd);
        return new SuccessResult("İlan başarılı bir şekilde eklendi");
    }

    @Override
    public Result setPasssive(int jobAdId) {
        JobAd jobAd=this.jobAdDao.getById(jobAdId);
        jobAd.setActive(false);
        jobAdDao.save(jobAd);
        return new SuccessResult("İş ilanı pasifleştirildi");
    }

    @Override
    public Result setActive(int jobAdId) {
        JobAd jobAd=this.jobAdDao.getById(jobAdId);
        jobAd.setActive(true);
        this.jobAdDao.save(jobAd);
        return new SuccessResult("İş ilanı aktifleştirildi");
    }

    @Override
    public DataResult<List<JobAd>> getAll() {
        return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findAll(),"Data listelendi");
    }

    @Override
    public DataResult<List<JobAd>> getActiveAds() {
        return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findByActive(true),"Aktif iş ilanları listelendi");
    }

    @Override
    public DataResult<List<JobAd>> getActiveAndOrderLastDate() {
        return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findByActiveOrderByLastDate(true),"Aktif iş ilanları tarihe göre listelendi");
    }

    @Override
    public DataResult<List<JobAd>> getActiveAndCompanyId(int id) {
        return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findByActiveTrueAndEmployer_Id(id),"Şirkere göre aktif iş işanları listelendi");
    }


}

package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.JobAdService;
import com.alihocaoglu.hrms.core.utilities.results.*;
import com.alihocaoglu.hrms.dataAccess.abstracts.*;
import com.alihocaoglu.hrms.entities.concretes.JobAd;
import com.alihocaoglu.hrms.entities.concretes.JobAdActivation;
import com.alihocaoglu.hrms.entities.dtos.JobAdDto;
import com.alihocaoglu.hrms.entities.dtos.JobAdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class JobAdManager implements JobAdService {

    private JobAdDao jobAdDao;
    private JobPositionDao jobPositionDao;
    private EmployerDao employerDao;
    private CityDao cityDao;
    private WorkPlaceDao workPlaceDao;
    private WorkTimeDao workTimeDao;
    private JobAdActivationDao jobAdActivationDao;
    private StaffDao staffDao;

    @Autowired
    public JobAdManager(JobAdDao jobAdDao,JobPositionDao jobPositionDao,EmployerDao employerDao,CityDao cityDao,WorkPlaceDao workPlaceDao,WorkTimeDao workTimeDao,JobAdActivationDao jobAdActivationDao,StaffDao staffDao) {
        this.jobAdDao = jobAdDao;
        this.jobPositionDao=jobPositionDao;
        this.employerDao=employerDao;
        this.cityDao=cityDao;
        this.workPlaceDao=workPlaceDao;
        this.workTimeDao=workTimeDao;
        this.jobAdActivationDao=jobAdActivationDao;
        this.staffDao=staffDao;
    }

    @Override
    public Result create(JobAdDto jobAdDto) {

        if(!cityDao.existsById(jobAdDto.getCityId())){
            return new ErrorResult("Şehir bulunamadı");
        }
        else if(!this.employerDao.existsById(jobAdDto.getEmployerId())){
            return new ErrorResult("İş veren bulunamadı");
        }
        else if(jobAdDto.getDescription().isEmpty()){
            return new ErrorResult("Açıklama boş birakılamaz");
        }
        else if(jobAdDto.getMinSalary()==0){
            return new ErrorResult("Minumum maaş 0 verilemez");
        }
        else if(jobAdDto.getMaxSalary()==0){
            return new ErrorResult("Maximum maaş sıfır verilemez");
        }
        else if(jobAdDto.getMinSalary() >= jobAdDto.getMaxSalary()){
            return new ErrorResult("Minumum maaş maksimum maala eşit yada büyük olamaz");
        }
        else if(jobAdDto.getOpenPositions()<1){
            return new ErrorResult("Açık pozisyon adeti 1 den küçük olamaz");
        }
        else if(jobAdDto.getLastDate() == null){
            return new ErrorResult("Son başvuru tarihi boş bırakılamaz");
        }else if(!this.workPlaceDao.existsById(jobAdDto.getWorkPlaceId())){
            return new ErrorResult("Geçersiz Çalışma yeri");
        }else if(!this.workTimeDao.existsById(jobAdDto.getWorkTimeId())){
            return new ErrorResult("Geçersiz çalışma zamanı");
        }

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
        jobAd.setActive(false);
        jobAd.setCreateDate(LocalDate.now());
        jobAd.setWorkPlace(this.workPlaceDao.getById(jobAdDto.getWorkPlaceId()));
        jobAd.setWorkTime(this.workTimeDao.getById(jobAdDto.getWorkTimeId()));
        jobAd.setConfirmed(false);
        this.jobAdDao.save(jobAd);

        JobAdActivation jobAdActivation=new JobAdActivation();
        jobAdActivation.setJobAdId(jobAd.getId());
        jobAdActivation.setConfirm(false);
        this.jobAdActivationDao.save(jobAdActivation);


        return new SuccessResult("İlan başarılı bir şekilde eklendi");
    }

    @Override
    public Result setPasssive(int jobAdId) {
        try {
            JobAd jobAd=this.jobAdDao.getById(jobAdId);
            jobAd.setActive(false);
            jobAdDao.save(jobAd);
            return new SuccessResult("İş ilanı pasifleştirildi");
        }catch (EntityNotFoundException exception){
            return new ErrorResult("İş ilanı bulunamadı");
        }

    }

    @Override
    public Result setActiveAndConfirm(int jobAdId,int staffId) {
        try{
            if(!this.staffDao.existsById(staffId)){
                return new ErrorResult("Böyle bir personel yok");
            }
            JobAdActivation jobAdActivation=this.jobAdActivationDao.getById(jobAdId);
            jobAdActivation.setConfirmDate(LocalDate.now());
            jobAdActivation.setConfirm(true);
            jobAdActivation.setStaffId(staffId);
            this.jobAdActivationDao.save(jobAdActivation);

            JobAd jobAd=this.jobAdDao.getById(jobAdId);
            jobAd.setActive(true);
            jobAd.setConfirmed(true);
            this.jobAdDao.save(jobAd);
            return new SuccessResult("İş ilanı aktifleştirildi");
        }catch (EntityNotFoundException exception){
            return new ErrorResult("İş ilanı bulunamadı");
        }

    }

    @Override
    public DataResult<List<JobAd>> getAll() {
        return new SuccessDataResult<List<JobAd>>(this.jobAdDao.findAll(),"Data listelendi");
    }

    @Override
    public DataResult<JobAd> getByJobAdId(int id) {
        if(!this.jobAdDao.existsById(id)){
            return new ErrorDataResult<JobAd>("Böyle bir ilan yok");
        }
        return new SuccessDataResult<JobAd>(this.jobAdDao.getById(id),"Data listelendi");
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

    @Override
    public DataResult<List<JobAd>> getByIsActiveAndPageNumberAndFilter(int pageNo, int pageSize, JobAdFilter jobAdFilter) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return new SuccessDataResult<List<JobAd>>(this.jobAdDao.getByFilter(jobAdFilter, pageable).getContent(), this.jobAdDao.getByFilter(jobAdFilter,pageable).getTotalElements()+"");
    }


}

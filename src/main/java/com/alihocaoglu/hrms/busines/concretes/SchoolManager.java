package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.SchoolService;
import com.alihocaoglu.hrms.core.utilities.results.*;
import com.alihocaoglu.hrms.dataAccess.abstracts.CvDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.SchoolDao;
import com.alihocaoglu.hrms.entities.concretes.School;
import com.alihocaoglu.hrms.entities.dtos.SchoolForSerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolManager implements SchoolService {

    private SchoolDao schoolDao;
    private CvDao cvDao;

    @Autowired
    public SchoolManager(SchoolDao schoolDao,CvDao cvDao) {
        this.schoolDao = schoolDao;
        this.cvDao=cvDao;
    }

    @Override
    public Result addSchool(SchoolForSerDto schoolForSerDto) {

        if(!this.cvDao.existsById(schoolForSerDto.getCvId())){
            return new ErrorResult("Böyle bir cv yok");
        }else if(schoolForSerDto.getName().length()<=2){
            return new ErrorResult("Okul adı 2 karakterden uzun olmalıdır");
        }else if(schoolForSerDto.getDepartment().length()<=2){
            return new ErrorResult("Bölüm adı 2 karakterden uzun olmalıdır");
        }else if(schoolForSerDto.getStartDate()==null){
            return new ErrorResult("Başlangıç tarihi boş birakılamaz");
        }

        School school=new School();
        school.setCv(this.cvDao.getById(schoolForSerDto.getCvId()));
        school.setName(schoolForSerDto.getName());
        school.setDepartment(schoolForSerDto.getDepartment());
        school.setStartDate(schoolForSerDto.getStartDate());
        school.setEndDate(schoolForSerDto.getEndDate());

        this.schoolDao.save(school);
        return new SuccessResult("Okul eklendi");
    }

    @Override
    public Result deleteSchool(int schoolId) {
        if(!this.schoolDao.existsById(schoolId)){
            return new ErrorResult("Böyle bir okul yok");
        }
        this.schoolDao.deleteById(schoolId);
        return new SuccessResult("Okul silindi");
    }

    @Override
    public DataResult<List<School>> getByCvId(int cvId) {
        if(this.schoolDao.findByCvId(cvId)==null){
            return new ErrorDataResult<List<School>>("Böyle bir cv yok");
        }
        return new SuccessDataResult<List<School>>(this.schoolDao.findByCvId(cvId),"Data listelendi");
    }
}

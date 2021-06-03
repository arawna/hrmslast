package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.ExperianceService;
import com.alihocaoglu.hrms.core.utilities.results.*;
import com.alihocaoglu.hrms.dataAccess.abstracts.CvDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.ExperianceDto;
import com.alihocaoglu.hrms.entities.concretes.Experiance;
import com.alihocaoglu.hrms.entities.dtos.ExperianceForSetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperianceManager implements ExperianceService {

    private ExperianceDto experianceDto;
    private CvDao cvDao;

    @Autowired
    public ExperianceManager(ExperianceDto experianceDto,CvDao cvDao) {
        this.experianceDto = experianceDto;
        this.cvDao=cvDao;
    }

    @Override
    public Result add(ExperianceForSetDto experianceForSetDto) {

        if(!this.cvDao.existsById(experianceForSetDto.getCvId())){
            return new ErrorResult("Böyle bir cv yok");
        }else if(experianceForSetDto.getCompanyName().length()<=2){
            return new ErrorResult("Şirket adı 2 karakterden uzun olmalıdır");
        }else if(experianceForSetDto.getPosition().length()<=2){
            return new ErrorResult("Pozisyon adı 2 karakterden uzun olmalıdır");
        }else if(experianceForSetDto.getStartDate() == null){
            return new ErrorResult("Başlangıç tarihi boş bırakılamaz");
        }

        Experiance experiance=new Experiance();
        experiance.setCv(this.cvDao.getById(experianceForSetDto.getCvId()));
        experiance.setCompanyName(experianceForSetDto.getCompanyName());
        experiance.setPosition(experianceForSetDto.getPosition());
        experiance.setStartDate(experianceForSetDto.getStartDate());
        experiance.setEndDate(experianceForSetDto.getEndDate());

        this.experianceDto.save(experiance);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result delete(int experianceId) {
        if(!this.experianceDto.existsById(experianceId)){
            return new ErrorResult("Böyle bir tecrübe yok");
        }
        this.experianceDto.deleteById(experianceId);
        return new SuccessResult("Silindi");
    }

    @Override
    public DataResult<List<Experiance>> getByCvId(int id) {

        return new SuccessDataResult<List<Experiance>>(this.experianceDto.findByCvId(id),"Data listelendi");
    }
}

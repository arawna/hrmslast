package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.ActivationByStaffService;
import com.alihocaoglu.hrms.busines.abstracts.ActivationCodeService;
import com.alihocaoglu.hrms.busines.abstracts.EmployerService;
import com.alihocaoglu.hrms.busines.abstracts.UserService;
import com.alihocaoglu.hrms.core.utilities.results.*;
import com.alihocaoglu.hrms.dataAccess.abstracts.EmployerDao;
import com.alihocaoglu.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmployerManager implements EmployerService {


    private EmployerDao employerDao;
    private UserService userService;
    private ActivationByStaffService activationByStaffService;
    private ActivationCodeService activationCodeService;

    @Autowired
    public EmployerManager(EmployerDao employerDao,UserService userService,ActivationByStaffService activationByStaffService,ActivationCodeService activationCodeService) {
        this.employerDao = employerDao;
        this.userService=userService;
        this.activationByStaffService=activationByStaffService;
        this.activationCodeService=activationCodeService;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Data listelendi");
    }

    @Override
    public DataResult<Employer> getByEmail(String email) {
        return new SuccessDataResult<Employer>(this.employerDao.findByEmail(email),"Listelendi");
    }

    @Override
    public Result add(Employer employer) {
       if(userService.getByEmail(employer.getEmail()).getData() != null){
            return new ErrorResult("Bu email zaten kayıtlı");
       }else if(!isEmailValid(employer.getEmail())){
           return new ErrorResult("Geçerli bir email giriniz");
       }else if(!employer.getEmail().endsWith(employer.getWebSite())){
           return new ErrorResult("Web siteniz ve emailinizin domaini aynı olmalıdır");
       }

       employer.setActive(false);
       employer.setMailVerify(false);
       this.employerDao.save(employer);

       activationCodeService.createActivationCode(employer);
       activationByStaffService.createActivationByStaff(employer);

       return new SuccessResult(employer.getEmail()+" Adresine doğrulama kodunuz gönderildi");

    }


    private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }



}

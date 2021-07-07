package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.*;
import com.alihocaoglu.hrms.core.utilities.results.*;
import com.alihocaoglu.hrms.dataAccess.abstracts.EmployerDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.EmployerUpdateDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.StaffDao;
import com.alihocaoglu.hrms.entities.concretes.Employer;
import com.alihocaoglu.hrms.entities.concretes.EmployerUpdate;
import com.alihocaoglu.hrms.entities.dtos.EmployerForRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmployerManager implements EmployerService {


    private EmployerDao employerDao;
    private UserService userService;
    private ActivationByStaffService activationByStaffService;
    private ActivationCodeService activationCodeService;
    private EmailService emailService;
    private EmployerUpdateDao employerUpdateDao;
    private StaffDao staffDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao,UserService userService,ActivationByStaffService activationByStaffService,ActivationCodeService activationCodeService,EmailService emailService,EmployerUpdateDao employerUpdateDao,StaffDao staffDao) {
        this.employerDao = employerDao;
        this.userService=userService;
        this.activationByStaffService=activationByStaffService;
        this.activationCodeService=activationCodeService;
        this.emailService=emailService;
        this.employerUpdateDao=employerUpdateDao;
        this.staffDao=staffDao;
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
    public Result add(EmployerForRegisterDto employerDto) {
        if(!employerDto.getPassword().equals(employerDto.getRePassword())){
            return new ErrorResult("Şifreler eşleşmiyor");
        }
        Employer employer=new Employer();
        employer.setEmail(employerDto.getEmail());
        employer.setPassword(employerDto.getPassword());
        employer.setCompanyName(employerDto.getCompanyName());
        employer.setWebSite(employerDto.getWebSite());
        employer.setPhoneNumber(employerDto.getPhoneNumber());
        employer.setWaitingUpdate(false);


       if(userService.getByEmail(employer.getEmail()).getData() != null){
            return new ErrorResult("Bu email zaten kayıtlı");
       }else if(!isEmailValid(employer.getEmail())){
           return new ErrorResult("Geçerli bir email giriniz");
       }else if(!employer.getEmail().endsWith(employer.getWebSite())){
           return new ErrorResult("Web siteniz ve emailinizin domaini aynı olmalıdır");
       }else if(employer.getPassword().length() <=6 ){
           return new ErrorResult("Şifre 6 karakterden uzun olmalıdır.");
       }else if(employer.getPhoneNumber().length() <10){
           return new ErrorResult("Geçerli bir telefon numarası giriniz.");
       }else if(employer.getCompanyName().length()<=2){
           return new ErrorResult("Şirket adı 2 karakterden uzun olmalıdır");
       }

       employer.setActive(false);
       employer.setMailVerify(false);
       this.employerDao.save(employer);

       this.emailService.sendVerifyEmail(employer,this.activationCodeService.createActivationCode(employer));
       activationByStaffService.createActivationByStaff(employer);



       return new SuccessResult(employer.getEmail()+" Adresine doğrulama kodunuz gönderildi");

    }

    @Override
    public DataResult<Employer> getById(int id) {
        if(!this.employerDao.existsById(id)){
            return new ErrorDataResult<Employer>("Böyle bir işveren yok");
        }
        return new SuccessDataResult<Employer>(this.employerDao.getById(id),"Data listelendi");
    }

    @Override
    public Result update(EmployerUpdate employerUpdate) {
        employerUpdate.setId(0);
        employerUpdate.setCreateDay(LocalDate.now());
        employerUpdate.setStaffId(null);

        if(employerUpdate.getEmployerId() == null){
            return new ErrorResult("İş veren id boş birakılamaz");
        }else if(employerUpdate.getEmail()==null){
            return new ErrorResult("Email boş bırakılamaz");
        }else if(employerUpdate.getCompanyName()==null){
            return new ErrorResult("Şirket adı boş bırakılamaz");
        }else if(employerUpdate.getPhoneNumber()==null){
            return new ErrorResult("Telefon numarası boş bırakılamaz");
        }else if(employerUpdate.getWebSite()==null){
            return new ErrorResult("Web sitesi boş bırakılamaz");
        }else if(employerUpdate.getCompanyName().length()<2){
            return new ErrorResult("Şirket adı 2 karakterden kısa olamaz");
        }else if(employerUpdate.getPhoneNumber().length()!=11){
            return new ErrorResult("Telefon numarası 11 haneli olmalıdır");
        }else if(!isEmailValid(employerUpdate.getEmail())){
            return new ErrorResult("Geçerli bir mail adresi giriniz");
        }else if(!this.employerDao.existsById(employerUpdate.getEmployerId())){
            return new ErrorResult(("Böyle bir işveren yok"));
        }
        Employer employer=this.employerDao.getById(employerUpdate.getEmployerId());
        this.employerUpdateDao.save(employerUpdate);
        employer.setWaitingUpdate(true);
        this.employerDao.save(employer);
        return new SuccessResult("Güncelleme isteği gönderildi personelin onayı ardından görünür olacaktır");
    }

    @Override
    public Result verifyUpdate(int employerUpdateId, int staffId) {
        if(!this.employerUpdateDao.existsById(employerUpdateId)){
            return new ErrorResult("Böyle bir güncelleme talebi yok");
        }else if(!this.staffDao.existsById(staffId)){
            return new ErrorResult("Böyle bir personel yok");
        }
        EmployerUpdate employerUpdate=this.employerUpdateDao.getById(employerUpdateId);
        Employer employer=this.employerDao.getById(employerUpdate.getEmployerId());

        employerUpdate.setVerifyed(true);
        employerUpdate.setStaffId(staffId);
        employerUpdate.setVerifyDate(LocalDate.now());
        this.employerUpdateDao.save(employerUpdate);

        employer.setEmail(employerUpdate.getEmail());
        employer.setCompanyName(employerUpdate.getCompanyName());
        employer.setPhoneNumber(employerUpdate.getPhoneNumber());
        employer.setWebSite(employerUpdate.getWebSite());
        employer.setWaitingUpdate(false);
        this.employerDao.save(employer);
        return new SuccessResult("Bilgiler güncellendi");
    }


    private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }



}

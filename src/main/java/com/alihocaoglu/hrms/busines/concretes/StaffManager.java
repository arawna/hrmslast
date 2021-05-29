package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.StaffService;
import com.alihocaoglu.hrms.busines.abstracts.UserService;
import com.alihocaoglu.hrms.core.utilities.results.*;
import com.alihocaoglu.hrms.dataAccess.abstracts.StaffDao;
import com.alihocaoglu.hrms.entities.concretes.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class StaffManager implements StaffService {

    private StaffDao staffDao;
    private UserService userService;

    @Autowired
    public StaffManager(StaffDao staffDao,UserService userService) {
        this.staffDao = staffDao;
        this.userService=userService;
    }

    @Override
    public Result create(Staff staff) {
        if(staff.getPassword().length() <=6){
            return new ErrorResult("Şifre 6 karakterden uzun olmalıdır");
        }else if(!isEmailValid(staff.getEmail())){
            return new ErrorResult("Email geçerli formatta değil");
        }else if(userService.getByEmail(staff.getEmail()).getData() != null){
            return new ErrorResult("Bu email zaten kayıtlı");
        }
        staff.setMailVerify(true);
        staffDao.save(staff);
        return new SuccessResult("Kayıt yapıldı");
    }

    @Override
    public DataResult<List<Staff>> getAll() {
        return new SuccessDataResult<List<Staff>>(this.staffDao.findAll(),"Data listelendi");
    }

    private final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }
}

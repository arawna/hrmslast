package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.ActivationByStaffService;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.core.utilities.results.SuccessResult;
import com.alihocaoglu.hrms.dataAccess.abstracts.ActivationByStaffDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.EmployerDao;
import com.alihocaoglu.hrms.entities.concretes.ActivationByStaff;
import com.alihocaoglu.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ActivationByStaffManager implements ActivationByStaffService {

    private ActivationByStaffDao activationByStaffDao;
    private EmployerDao employerDao;

    @Autowired
    public ActivationByStaffManager(ActivationByStaffDao activationByStaffDao,EmployerDao employerDao) {
        this.activationByStaffDao = activationByStaffDao;
        this.employerDao=employerDao;
    }

    @Override
    public void createActivationByStaff(Employer employer) {
        ActivationByStaff activationByStaff=new ActivationByStaff();
        activationByStaff.setEmployeId(employer.getId());
        activationByStaff.setVerifyed(false);
        activationByStaffDao.save(activationByStaff);
    }

    @Override
    public Result activateEmployer(int activationByStaffId) {
        Employer employer = employerDao.getById(activationByStaffDao.getById(activationByStaffId).getEmployeId());
        employer.setActive(true);
        employerDao.save(employer);

        ActivationByStaff activationByStaff = activationByStaffDao.getById(activationByStaffId);
        activationByStaff.setVerifyed(true);
        activationByStaff.setVerifyDate(LocalDate.now());
        activationByStaffDao.save(activationByStaff);

        return new SuccessResult("Kullanıcı aktif edildi");
    }
}

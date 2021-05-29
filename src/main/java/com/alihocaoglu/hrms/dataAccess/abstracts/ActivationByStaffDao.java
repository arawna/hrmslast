package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.ActivationByStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationByStaffDao extends JpaRepository<ActivationByStaff,Integer> {
    ActivationByStaff findByEmployeId(int id);

}

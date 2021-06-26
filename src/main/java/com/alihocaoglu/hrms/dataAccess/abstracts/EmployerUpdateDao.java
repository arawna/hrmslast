package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.EmployerUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerUpdateDao extends JpaRepository<EmployerUpdate, Integer> {
}

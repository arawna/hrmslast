package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.JobAdActivation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobAdActivationDao extends JpaRepository<JobAdActivation,Integer> {
}

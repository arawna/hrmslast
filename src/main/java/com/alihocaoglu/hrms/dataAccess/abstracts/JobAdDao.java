package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.JobAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdDao extends JpaRepository<JobAd,Integer> {
    List<JobAd> findByActive(boolean active);
    List<JobAd> findByActiveOrderByLastDate(boolean active);
    List<JobAd> findByActiveTrueAndEmployer_Id(int id);
}

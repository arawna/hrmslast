package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTimeDao extends JpaRepository<WorkTime,Integer> {
}

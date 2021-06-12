package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkPlaceDao extends JpaRepository<WorkPlace,Integer> {
}

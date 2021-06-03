package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.Cv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvDao extends JpaRepository<Cv,Integer> {
    Cv findByCandidateId(int id);
}

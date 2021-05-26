package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDao extends JpaRepository<Candidate,Integer> {
    Candidate findByNationalNumber(String nationalNumber);
    Candidate findByEmail(String email);
}

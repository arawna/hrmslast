package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.NationalValidationService;
import com.alihocaoglu.hrms.entities.concretes.Candidate;
import org.springframework.stereotype.Service;

@Service
public class MernisManager implements NationalValidationService {


    @Override
    public boolean validate(Candidate candidate) {
        if(candidate.getFirstName().length()<2){
            return false;
        }else if(candidate.getLastName().length()<2){
            return false;
        }else if(candidate.getNationalNumber().length()!=11){
            return false;
        }else if(candidate.getDateOfBirth()==null){
            return false;
        }
        return true;
    }
}

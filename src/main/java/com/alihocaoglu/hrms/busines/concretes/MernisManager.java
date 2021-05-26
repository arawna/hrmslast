package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.NationalValidationService;
import com.alihocaoglu.hrms.entities.concretes.Candidate;
import com.alihocaoglu.hrms.mernis.RENKPSPublicSoap;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class MernisManager implements NationalValidationService {

    RENKPSPublicSoap kpsSoap=new RENKPSPublicSoap();

    @Override
    public boolean validate(Candidate candidate) {
        try {
            return kpsSoap.TCKimlikNoDogrula(
                    Long.parseLong(candidate.getNationalNumber()),
                    candidate.getFirstName().toUpperCase(),
                    candidate.getLastName().toUpperCase(),
                    candidate.getDateOfBirth().get(Calendar.YEAR)
            );
        } catch (Exception e) {
            return false;
        }
    }
}

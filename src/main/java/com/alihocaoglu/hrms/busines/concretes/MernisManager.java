package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.NationalValidationService;
import com.alihocaoglu.hrms.entities.concretes.Candidate;
import com.alihocaoglu.hrms.mernis.KPSPublicSoap;
import org.springframework.stereotype.Service;

@Service
public class MernisManager implements NationalValidationService {

    KPSPublicSoap kpsSoap=new KPSPublicSoap();

    @Override
    public boolean validate(Candidate candidate) {
        try {
            return kpsSoap.TCKimlikNoDogrula(
                    Long.parseLong(candidate.getNationalNumber()),
                    candidate.getFirstName().toUpperCase(),
                    candidate.getLastName().toUpperCase(),
                    candidate.getDateOfBirth().getYear()
            );
        } catch (Exception e) {
            return false;
        }
    }
}

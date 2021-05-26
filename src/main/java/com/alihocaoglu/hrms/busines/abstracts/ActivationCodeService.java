package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.ActivationCode;
import com.alihocaoglu.hrms.entities.concretes.User;

public interface ActivationCodeService {
    ActivationCode getByCode(String code);
    String createActivationCode(User user);
    Result activateUser(String code);
}

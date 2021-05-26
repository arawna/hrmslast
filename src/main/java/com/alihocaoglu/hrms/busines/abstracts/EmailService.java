package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.entities.concretes.User;

public interface EmailService {
    void sendVerifyEmail(User user,String code);
}

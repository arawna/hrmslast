package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.EmailService;
import com.alihocaoglu.hrms.entities.concretes.User;
import org.springframework.stereotype.Service;

@Service
public class EmailManager implements EmailService {
    @Override
    public void sendVerifyEmail(User user, String code) {
        //email gönderme kodları
    }
}

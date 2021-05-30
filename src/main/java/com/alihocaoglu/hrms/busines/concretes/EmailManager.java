package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.EmailService;
import com.alihocaoglu.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailManager implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendVerifyEmail(User user, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("HRMS Mail Dogrulama");
        message.setText("Hrms kayıt işleminizi tamamlamak için linke tıklayınız: localhost:8080/api/activationcode/active/"+code);
        message.setTo(user.getEmail());
        message.setFrom("deneme@gmail.com");


        emailSender.send(message);

        //email gönderme kodları
    }
}

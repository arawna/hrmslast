package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.ActivationCodeService;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.core.utilities.results.SuccessResult;
import com.alihocaoglu.hrms.dataAccess.abstracts.ActivationCodeDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.UserDao;
import com.alihocaoglu.hrms.entities.concretes.ActivationCode;
import com.alihocaoglu.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class ActivationCodeManager implements ActivationCodeService {

    private ActivationCodeDao activationCodeDao;
    private UserDao userDao;

    @Autowired
    public ActivationCodeManager(ActivationCodeDao activationCodeDao,UserDao userDao) {
        this.activationCodeDao = activationCodeDao;
        this.userDao=userDao;
    }

    @Override
    public ActivationCode getByCode(String code) {
        return this.activationCodeDao.findByCode(code);
    }

    String generatedCode="";
    @Override
    public String createActivationCode(User user) {

        for(int i=0;i==0;i=0){
            generatedCode = rastgeleDegerSaglayici(16);

            if(getByCode(generatedCode) == null){
                break;
            }
        }

        ActivationCode activationCode=new ActivationCode();
        activationCode.setUserId(user.getId());
        activationCode.setCode(generatedCode);

        activationCodeDao.save(activationCode);

        return generatedCode;
    }

    @Override
    public Result activateUser(String code) {
        User user = userDao.getById(activationCodeDao.findByCode(code).getUserId());
        user.setMailVerify(true);
        userDao.save(user);
        return new SuccessResult("Kullanıcı aktif edildi");
    }

    private final String nelerOlsun = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private SecureRandom karistirici = new SecureRandom();
    private String rastgeleDegerSaglayici(int uzunluk){
        StringBuilder rastgeleDegerYapici = new StringBuilder(uzunluk);
        for(int i=0;i<uzunluk;i++){
            rastgeleDegerYapici.append(nelerOlsun.charAt(karistirici.nextInt(nelerOlsun.length())));
        }
        return rastgeleDegerYapici.toString();
    }


}

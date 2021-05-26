package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}

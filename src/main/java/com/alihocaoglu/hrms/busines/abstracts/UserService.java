package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.entities.concretes.User;
import com.alihocaoglu.hrms.entities.dtos.UserLoginDto;
import com.alihocaoglu.hrms.entities.dtos.UserLoginReturnDto;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();
    DataResult<User> getByEmail(String email);
    DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto);
    DataResult<List<User>> getVerifyedUsers();
}

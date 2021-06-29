package com.alihocaoglu.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginReturnDto {
    private int id;
    private String name;
    private String email;
    private int userType;
}

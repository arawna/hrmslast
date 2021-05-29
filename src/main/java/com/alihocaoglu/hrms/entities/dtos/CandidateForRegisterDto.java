package com.alihocaoglu.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateForRegisterDto {
    private String email;
    private String password;
    private String rePassword;
    private String firstName;
    private String lastName;
    private String nationalNumber;
    private LocalDate birthDate;
}

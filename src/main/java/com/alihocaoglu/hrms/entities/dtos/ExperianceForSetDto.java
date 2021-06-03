package com.alihocaoglu.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperianceForSetDto {

    private int cvId;

    private String companyName;

    private String position;

    private LocalDate startDate;

    private LocalDate endDate;
}

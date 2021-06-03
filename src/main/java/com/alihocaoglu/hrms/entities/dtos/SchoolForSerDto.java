package com.alihocaoglu.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolForSerDto {
    private int CvId;
    private String name;
    private String department;
    private LocalDate startDate;
    private LocalDate endDate;
}

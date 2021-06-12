package com.alihocaoglu.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdDto {

    private int jobPositionId;

    private int employerId;

    private String description;

    private int cityId;

    private int minSalary;

    private int maxSalary;

    private int openPositions;

    private LocalDate lastDate;

    private boolean active;

    private int workPlaceId;

    private int workTimeId;

}

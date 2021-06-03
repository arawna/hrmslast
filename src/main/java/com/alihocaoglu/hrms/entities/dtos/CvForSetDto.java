package com.alihocaoglu.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvForSetDto {
    private int candidateId;
    private String github;
    private String linkedin;
    private String biography;
}

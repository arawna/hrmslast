package com.alihocaoglu.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageForSetDto {
    private int cvId;
    private String name;
    private String level;
}

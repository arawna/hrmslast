package com.alihocaoglu.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_ad_activation")
public class JobAdActivation {

    @Id
    @Column(name = "job_ad_id")
    private int jobAdId;

    @Column(name = "staff_id")
    private Integer staffId;

    @Column(name = "confirm")
    private boolean confirm;

    @Column(name = "confirm_date")
    private LocalDate confirmDate;
}

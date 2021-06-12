package com.alihocaoglu.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_ads")
public class JobAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToOne()
    @JoinColumn(name = "jobposition_id")
    private JobPosition jobPosition;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "min_salary")
    private int minSalary;

    @Column(name = "max_salary")
    private int maxSalary;

    @Column(name = "open_positions")
    private int openPositions;

    @Column(name = "last_date")
    private LocalDate lastDate;

    @Column(name = "active")
    private boolean active;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToOne()
    @JoinColumn(name = "workplace_id")
    private WorkPlace workPlace;

    @ManyToOne()
    @JoinColumn(name = "work_time_id")
    private WorkTime workTime;

    private boolean confirmed;
}

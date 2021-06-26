package com.alihocaoglu.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","jobAds"})
public class Employer extends User{

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "website")
    private String webSite;

    @Column(name = "isactived")
    private boolean isActive;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "waiting_update")
    private boolean waitingUpdate;

    @OneToMany(mappedBy = "employer")
    private List<JobAd> jobAds;
}

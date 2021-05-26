package com.alihocaoglu.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "employers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Employer extends User{

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "website")
    private String webSite;

    @Column(name = "isactived")
    private boolean isActive;

    @Column(name = "phone_number")
    private String phoneNumber;
}

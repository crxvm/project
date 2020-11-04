package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "citizenship_Code")
    private String citizenshipCode;

    @Column(name = "citizenship_Name")
    private String citizenshipName;

}

package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Сущность стран из справочника
 */
@Data
@Entity(name = "Country")
public class Country {
    /**
     * Уникальный идентефикатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Код страны гражданства
     */
    @Column(name = "citizenship_Code")
    private String citizenshipCode;

    /**
     * Название страны гражданства
     */
    @Column(name = "citizenship_Name")
    private String citizenshipName;

}

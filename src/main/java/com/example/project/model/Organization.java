package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Organization")
public class Organization {
    public Organization() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="full_name", length = 50, nullable = false)
    private String fullName;

    @Column(name = "inn", length = 25, nullable = false)
    private String inn;

    @Column(name = "kpp", length = 25, nullable = false)
    private String kpp;

    @Column(name = "address", length = 25, nullable = false)
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "is_Active")
    private Boolean isActive;

    public Organization(String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }
}

package com.example.project.model;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import org.springframework.jdbc.core.SqlReturnType;

import javax.persistence.*;

@Data
@Entity(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="FullName", length = 50, nullable = false)
    private String fullName;

    @Column(name = "inn", length = 25, nullable = false)
    private String inn;

    @Column(name = "kpp", length = 25, nullable = false)
    private String kpp;

    @Column(name = "address", length = 25, nullable = false)
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "isActive")
    private Boolean isActive;

}

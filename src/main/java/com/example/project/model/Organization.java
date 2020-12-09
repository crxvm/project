package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Сущность организации
 */
@Data
@Entity(name = "Organization")
public class Organization {
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Поле версии hibernate
     */
    @Version
    private Integer version = 0;

    /**
     * Название организации
     */
    @Column(name="name", length = 50, nullable = false)
    private String name;

    /**
     * Полное название организации
     */
    @Column(name="full_name", length = 50, nullable = false)
    private String fullName;

    /**
     * Индивидуальный номер налогоплательщика
     */
    @Column(name = "inn", length = 25, nullable = false)
    private String inn;

    /**
     * Код причины постановки
     */
    @Column(name = "kpp", length = 25, nullable = false)
    private String kpp;

    /**
     * Адрес организации
     */
    @Column(name = "address", length = 25, nullable = false)
    private String address;

    /**
     * Телефон организации
     */
    @Column(name = "phone", length = 15)
    private String phone;

    /**
     * Статус активности
     */
    @Column(name = "is_Active")
    private Boolean isActive;

    public Organization() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

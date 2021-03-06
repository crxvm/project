package com.example.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Сущность офис
 */
@Data
@Entity(name = "Office")
@NoArgsConstructor
public class Office {
    /**
     * Уникальный идентификатор
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
     * идентификатор организации
     */
    @Column(name ="org_Id")
    private Integer orgId;

    /**
     * Название офиса
     */
    @Column(name = "name")
    private String name;

    /**
     * Адрес офиса
     */
    @Column(name = "address")
    private String address;

    /**
     * Телефон офиса
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Статус активности
     */
    @Column(name = "is_Active")
    private Boolean isActive;

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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

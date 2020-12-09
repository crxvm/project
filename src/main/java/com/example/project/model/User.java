package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Сущность пользователя
 */
@Entity(name = "User")
@Data
public class User {
    /**
     * Уникальный идентефикатор
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
     * Имя
     */
    @Column(name = "first_Name")
    private String firstName;

    /**
     * Идентефикатор офиса
     */
    @Column(name = "office_Id")
    private Integer officeId;

    /**
     * Фамилия
     */
    @Column(name = "second_Name")
    private String secondName;

    /**
     * Отчество
     */
    @Column(name = "middle_Name")
    private String middleName;

    /**
     * Должность
     */
    @Column(name = "position")
    private String position;

    /**
     * Телефон
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Статус идентефикации
     */
    @Column(name = "is_Identified")
    private Boolean isIdentified;

    /**
     * Join столбец для связи с сущностью документов пользователя
     */
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER ,cascade = CascadeType.ALL) //выбрать нужный
    @PrimaryKeyJoinColumn
    private UserDocument userDocument;

    /**
     * Join столбец для свзяи с сущностью стран
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_Id")
    private Country country;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public UserDocument getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(UserDocument userDocument) {
        this.userDocument = userDocument;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}

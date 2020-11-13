package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version = 0;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "office_Id")
    private Integer officeId;

    @Column(name = "second_Name")
    private String secondName;

    @Column(name = "middle_Name")
    private String middleName;

    @Column(name = "position")
    private String position;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_Identified")
    private Boolean isIdentified;

    public Long getId() {
        return id;
    }

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY ,cascade = CascadeType.ALL) //выбрать нужный
    @PrimaryKeyJoinColumn
    private UserDocument userDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_Id")
    private Country country;

}

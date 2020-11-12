package com.example.project.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "User_Document")
public class UserDocument {

    @Id
    @Column(name = "user_Id")
    private Long userId;

    @Version
    private Integer version = 0;

    @Column(name = "doc_Number")
    private String docNumber;

    @Column(name = "doc_Date")
    private Date docDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "User_Id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_Id")
    private Document document;

}

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

    @Column(name = "document_Id")
    private Integer documentId;

    @Column(name = "doc_Number")
    private String docNumber;

    @Column(name = "doc_Date")
    private Date docDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "User_Id")
    private User user;

}

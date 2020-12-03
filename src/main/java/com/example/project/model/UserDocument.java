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

    @Temporal(TemporalType.DATE)
    @Column(name = "doc_Date")
    private Date docDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "User_Id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "document_Id")
    private Document document;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}

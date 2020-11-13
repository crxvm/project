package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "doc_Code")
    private String docCode;

    @Column(name = "doc_Name")
    private String docName;

    public String getDocName() {
        return docName;
    }
}

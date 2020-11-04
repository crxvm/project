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

    @Column(name = "docCode")
    private String docCode;

    @Column(name = "docName")
    private String docName;



}

package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Сущность документов из справочника
 */
@Data
@Entity(name = "Document")
public class Document {
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
    private Integer version;

    /**
     * Код документа
     */
    @Column(name = "doc_Code")
    private String docCode;

    /**
     * Название документа
     */
    @Column(name = "doc_Name")
    private String docName;

    public String getDocName() {
        return docName;
    }
}

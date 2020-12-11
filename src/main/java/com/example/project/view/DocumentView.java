package com.example.project.view;

import javax.validation.constraints.NotNull;

/**
 * Отображение для документов из справочника
 */
public class DocumentView {
    /**
     * Уникальный идентификатор
     */
    @NotNull
    public String id;

    /**
     * Код документа
     */
    public String docCode;

    /**
     * Название документа
     */
    public String docName;
}

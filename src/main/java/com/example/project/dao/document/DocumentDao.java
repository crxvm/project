package com.example.project.dao.document;

import com.example.project.model.Document;

import java.util.List;

/**
 * DAO для работы с сущностями документов
 */
public interface DocumentDao {

    /**
     * Возвращает список сущностей всех документов
     * @return список объектов {@link Document}
     */
    List<Document> all();

    /**
     * Получить сущность по полю code
     * @param code код документа
     * @return объект {@link Document}
     */
    Document getByCode(String code);

    /**
     * Получить сущность по полю docName
     * @param docName название документа
     * @return объект {@link Document}
     */
    Document getByName(String docName);

    /**
     * Получить сущность по полям docName и docCode
     * @param docName имя документа
     * @param docCode код документя
     * @return
     */
    Document getByNameAndCode(String docName, String docCode);
}

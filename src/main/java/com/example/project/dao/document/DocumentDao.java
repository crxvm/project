package com.example.project.dao.document;

import com.example.project.model.Document;

import java.util.List;

public interface DocumentDao {
    List<Document> all();
    Document getByCode(String code);
    Document getByName(String docName);
}

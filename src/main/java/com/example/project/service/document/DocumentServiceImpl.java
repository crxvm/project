package com.example.project.service.document;

import com.example.project.dao.document.DocumentDao;
import com.example.project.model.Document;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.DocumentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentDao dao;
    private final MapperFacade mapperFacade;
    @Autowired
    public DocumentServiceImpl(DocumentDao dao, MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentView> documents() {
        List<Document> all = dao.all();
        return mapperFacade.mapAsList(all, DocumentView.class);
    }
}

package com.example.project.controller.doc;

import com.example.project.service.document.DocumentService;
import com.example.project.view.DocumentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Контроллер для справочника документов
 */
@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Возвращает список документов из справочника
     *
     * @return список объекто {@link DocumentView}
     */
    @GetMapping
    public List<DocumentView> documents(){
        return documentService.documents();
    }

}

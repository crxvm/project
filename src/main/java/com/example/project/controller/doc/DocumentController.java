package com.example.project.controller.doc;

import com.example.project.service.document.DocumentService;
import com.example.project.view.DocumentView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "DocumentController", description = "Справочная информация о документах")
@RestController
@RequestMapping(value = "/api/docs")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @ApiOperation(value = "Получить всю информацию о документах", httpMethod = "GET")
    @GetMapping(value = "/")
    public List<DocumentView> documents(){
        return documentService.documents();
    }

}

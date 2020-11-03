package com.example.project.service.document;

import com.example.project.view.DocumentView;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface DocumentService {
    List<DocumentView> documents();
}

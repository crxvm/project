package com.example.project.service.document;

import com.example.project.view.DocumentView;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Сервис для управления информацией справочника документов
 * получая отображение и преобразуя в сущность для передачи в DAO слой
 */
@Validated
public interface DocumentService {
    /**
     * Получить отображение документов из справочника
     * @return {@link DocumentView}
     */
    List<DocumentView> documents();
}

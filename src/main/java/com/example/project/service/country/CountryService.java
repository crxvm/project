package com.example.project.service.country;

import com.example.project.view.CountryView;

import java.util.List;

/**
 * Сервис для управления информацией справочника стран получая отображение и преобразуя в сущность
 * для передачи в DAO слой
 */
public interface CountryService {
    /**
     * Получить отображение всех стран из справочника
     * @return {@link CountryView}
     */
    List<CountryView> countries();
}

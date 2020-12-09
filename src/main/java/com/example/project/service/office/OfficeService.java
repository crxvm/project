package com.example.project.service.office;

import com.example.project.view.office.*;

import java.util.List;

/**
 * Сервис для управления информацией об офисах
 * получая отображение и преобразуя в сущность для передачи в DAO слой
 */
public interface OfficeService {
    /**
     * Получить сущность офиса по параметру id и преобразовать ее в отображение
     * @param id уникальный идентификатор сущности
     * @return {@link OfficeView}
     */
    OfficeView getById(Long id);

    /**
     * Преобразовать отображение и передать его в DAO для обновления данных сущности
     * @param officeUpdateView {@link OfficeUpdateView}
     */
    void update(OfficeUpdateView officeUpdateView);

    /**
     * Преобразовать отображение и передать его в DAO для сохранения сущности
     * @param officeSaveView
     */
    void save(OfficeSaveView officeSaveView);

    /**
     * Получить список отображений и преобразовать офис передав отображение в DAO
     * @param officeListInView
     * @return список объектов {@link OfficeListOutView}
     */
    List<OfficeListOutView> list(OfficeListInView officeListInView);
}

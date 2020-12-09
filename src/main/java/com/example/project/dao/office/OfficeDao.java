package com.example.project.dao.office;

import com.example.project.model.Office;

import java.util.List;

/**
 * DAO для работы с сущностями офисов
 */
public interface OfficeDao {
    /**
     * Сохранить сущность офис
     * @param office {@link Office}
     */
    void save(Office office);

    /**
     * Обновить данные сущности офис
     * @param office {@link Office}
     */
    void update(Office office);

    /**
     * Получить сущность офис по полю id
     * @param id уникальный идентификатор
     * @return объект {@link Office}
     */
    Office getById(Long id);

    /**
     * Получить сущность по полям:
     * @param orgId идентификатор сущности организации
     * @param name название офиса
     * @param phone телефон офиса
     * @param isActive статус активности
     * @return список сущностей {@link Office}
     */
    List<Office> list(Integer orgId, String name, String phone, Boolean isActive);
}

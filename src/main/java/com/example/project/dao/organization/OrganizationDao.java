package com.example.project.dao.organization;

import com.example.project.model.Organization;

import java.util.List;

/**
 * DAO для работы с сущностями организации
 */
public interface OrganizationDao {
    /**
     * Сохранить сущность организации
     * @param organization {@link Organization}
     */
    void save(Organization organization);

    /**
     * Получить сущность организации по полю id
     * @param id уникальный идентификатор
     * @return объект {@link Organization}
     */
    Organization getOrganizationById(Long id);

    /**
     * Обновить данные организации
     * @param organization {@link Organization}
     */
    void update (Organization organization);

    /**
     * Получить сущность организации по полям:
     * @param name название организации
     * @param inn инн
     * @param isActive статус активности
     * @return список объектов {@link Organization}
     */
    List<Organization> list (String name, String inn, Boolean isActive);
}

package com.example.project.service.organization;

import com.example.project.model.Organization;
import com.example.project.view.organization.OrganizationListInView;
import com.example.project.view.organization.OrganizationSaveView;
import com.example.project.view.organization.OrganizationView;
import com.example.project.view.organization.OrganizationListOutView;

import java.util.List;

/**
 * Сервис для управления информацией об организациях
 * получая отображение и преобразуя в сущность для передачи в DAO слой
 */
public interface OrganizationService {
    /**
     * Получить сущность организации по параметру id и преобразовать ее в отображение
     * @param id уникальный идентификатор сущности
     * @return отображение {@link OrganizationView}
     */
    OrganizationView getById(Long id);

    /**
     * Преобразовать отображение и передать его в DAO для сохранения новой сущности организации
     * @param organizationSaveView {@link OrganizationSaveView}
     */
    void save(OrganizationSaveView organizationSaveView);

    /**
     * Преобразовать отображение и передать его в DAO для обновления сущности организации
     * @param organizationView {@link OrganizationView}
     */
    void update(OrganizationView organizationView);

    /**
     * Получить список отображений, и преображзовать параметр для передачи в DAO
     * @param organizationListInView {@link OrganizationListInView}
     * @return список объектов {@link OrganizationListOutView}
     */
    List<OrganizationListOutView> list(OrganizationListInView organizationListInView);
}

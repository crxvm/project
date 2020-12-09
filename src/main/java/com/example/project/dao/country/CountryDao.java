package com.example.project.dao.country;

import com.example.project.model.Country;

import java.util.List;

/**
 * DAO для работы с сущностями стран
 */
public interface CountryDao {
    /**
     * Возвращает список сущностей стран
     * @return список объектов {@link Country}
     */
    List<Country> all();

    /**
     * Получить сущность по code
     * @param code код страны
     * @return объект {@link Country}
     */
    Country getByCode(String code);
}

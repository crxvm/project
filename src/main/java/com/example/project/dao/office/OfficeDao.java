package com.example.project.dao.office;

import com.example.project.model.Office;


public interface OfficeDao {
    void save(Office office);
    void update(Office office);
    Office getById(Long id);
    Office list(Integer orgId, String name, String phone, Boolean isActive);
}

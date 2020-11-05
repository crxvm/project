package com.example.project.dao.office;

import com.example.project.view.Office;

public interface OfficeDao {
    void save(Office office);
    void update(Office office);
    Office getById(Long id);
    Office list(String org_Id, String name, String phone, Boolean is_Active);
}

package com.example.project.dao.organization;

import com.example.project.model.Organization;


public interface OrganizationDao {
    void save(Organization organization);
    Organization getOrganizationById(Long id);
    void update (Organization organization);
    Organization list (Long id, String inn, Boolean is_Active);
}

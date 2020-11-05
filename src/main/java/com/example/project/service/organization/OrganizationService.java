package com.example.project.service.organization;

import com.example.project.view.OrganizationFullView;
import com.example.project.view.OrganizationListView;

import java.util.List;

public interface OrganizationService {
    OrganizationFullView getById(Long id);
    void save(OrganizationFullView organizationFullView);
    void update(OrganizationFullView organizationFullView);
    List<OrganizationListView> list(Long id, String inn, Boolean is_Active);
}

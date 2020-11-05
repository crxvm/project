package com.example.project.service.organization;

import com.example.project.view.OrganizationFullView;
import com.example.project.view.OrganizationListView;

import java.util.List;

public interface OrganizationService {
    OrganizationFullView getById(Long id);
    void save(OrganizationFullView organizationFullView);
    void update(OrganizationFullView organizationFullView);
    OrganizationListView list(String name, String inn, Boolean is_Active);
}

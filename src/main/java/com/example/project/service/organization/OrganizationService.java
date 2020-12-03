package com.example.project.service.organization;

import com.example.project.model.Organization;
import com.example.project.view.organization.OrganizationListInView;
import com.example.project.view.organization.OrganizationSaveView;
import com.example.project.view.organization.OrganizationView;
import com.example.project.view.organization.OrganizationListOutView;

import java.util.List;

public interface OrganizationService {
    OrganizationView getById(Long id);
    void save(OrganizationSaveView organizationSaveView);
    void update(OrganizationView organizationView);
    List<OrganizationListOutView> list(OrganizationListInView organizationListInView);
}

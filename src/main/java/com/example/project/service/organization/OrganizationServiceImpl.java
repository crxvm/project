package com.example.project.service.organization;

import com.example.project.dao.organization.OrganizationDao;
import com.example.project.model.Organization;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.organization.OrganizationListInView;
import com.example.project.view.organization.OrganizationSaveView;
import com.example.project.view.organization.OrganizationView;
import com.example.project.view.organization.OrganizationListOutView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    private final OrganizationDao dao;
    private final MapperFacade mapperFacade;


    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Transactional(readOnly = true)
    @Override
    public OrganizationView getById(Long id) {
        Organization organization = dao.getOrganizationById(id);
        return mapperFacade.map(organization, OrganizationView.class);
    }

    @Transactional
    @Override
    public List<OrganizationListOutView> list(OrganizationListInView view) {
        List<Organization> organizations = dao.list(view.name, view.inn, view.isActive);
        return mapperFacade.mapAsList(organizations, OrganizationListOutView.class);
    }

    @Transactional
    @Override
    public void save(OrganizationSaveView organizationSaveView) {
        dao.save(mapperFacade.map(organizationSaveView, Organization.class));
    }

    @Override
    @Transactional
    public void update(OrganizationView organizationView) {
        dao.update(mapperFacade.map(organizationView, Organization.class));
    }
    }

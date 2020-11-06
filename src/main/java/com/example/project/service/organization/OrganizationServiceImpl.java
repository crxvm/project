package com.example.project.service.organization;

import com.example.project.dao.organization.OrganizationDao;
import com.example.project.model.Organization;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.OrganizationFullView;
import com.example.project.view.OrganizationListView;
import org.hibernate.loader.plan.exec.internal.OneToManyLoadQueryDetails;
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
    public OrganizationFullView getById(Long id) {
        Organization organization = dao.getOrganizationById(id);
        return mapperFacade.map(organization, OrganizationFullView.class);
    }

    @Transactional
    @Override
    public OrganizationListView list(String name, String inn, Boolean isActive) {
        Organization organizations = dao.list(name, inn, isActive);
        return mapperFacade.map(organizations, OrganizationListView.class);
    }

    @Transactional
    @Override
    public void save(OrganizationFullView organizationFullView) {
        dao.save(mapperFacade.map(organizationFullView, Organization.class));
    }


    @Override
    @Transactional
    public void update(OrganizationFullView organizationFullView) {
        dao.update(mapperFacade.map(organizationFullView, Organization.class));
    }
    }

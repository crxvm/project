package com.example.project.service.organization;

import com.example.project.dao.organization.OrganizationDao;
import com.example.project.model.Organization;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.OrganizationFullView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    private final OrganizationDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public OrganizationFullView getById(Long id) {
        Organization organization = dao.getOrganizationById(id);
        return mapperFacade.map(organization, OrganizationFullView.class);
    }
}

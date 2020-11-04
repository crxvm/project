package com.example.project.service.organization;

import com.example.project.dao.organization.OrganizationDao;
import com.example.project.model.Organization;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.OrganizationFullView;
import org.hibernate.loader.plan.exec.internal.OneToManyLoadQueryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void save(OrganizationFullView organizationFullView) {
        Organization organization = new Organization(organizationFullView.name, organizationFullView.fullName,
                organizationFullView.inn, organizationFullView.kpp, organizationFullView.address, organizationFullView.phone,
                organizationFullView.is_Active);
        dao.save(organization);
    }

}

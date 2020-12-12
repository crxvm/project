package com.example.project.service.office;

import com.example.project.dao.office.OfficeDao;
import com.example.project.dao.organization.OrganizationDao;
import com.example.project.model.Office;
import com.example.project.model.Organization;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.office.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService{
    private final OfficeDao dao;
    private final MapperFacade mapperFacade;
    private final OrganizationDao organizationDao;
    @Autowired
    public OfficeServiceImpl(OfficeDao dao, MapperFacade mapperFacade, OrganizationDao organizationDao) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView getById(Long id) {
        Office office = dao.getById(id);
        return mapperFacade.map(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeUpdateView officeUpdateView) {
        dao.update(mapperFacade.map(officeUpdateView, Office.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OfficeSaveView officeSaveView) {
        if(organizationDao.getOrganizationById(officeSaveView.orgId) == null) {
            throw new NoResultException("Cannot find organization with id: " + officeSaveView.orgId);
        }
        dao.save(mapperFacade.map(officeSaveView, Office.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OfficeListOutView> list(OfficeListInView officeListInView) {
        List<Office> office = dao.list(officeListInView.orgId, officeListInView.name,officeListInView.phone,officeListInView.isActive);
        return mapperFacade.mapAsList(office, OfficeListOutView.class);
    }
}

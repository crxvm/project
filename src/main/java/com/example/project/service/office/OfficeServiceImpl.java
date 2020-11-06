package com.example.project.service.office;

import com.example.project.dao.office.OfficeDao;
import com.example.project.model.Office;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.OfficeFullView;
import com.example.project.view.OfficeListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OfficeServiceImpl implements OfficeService{
    private final OfficeDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public OfficeFullView getById(Long id) {
        Office office = dao.getById(id);
        return mapperFacade.map(office, OfficeFullView.class);
    }

    @Override
    @Transactional
    public void update(OfficeFullView officeFullView) {
        dao.update(mapperFacade.map(officeFullView, Office.class));
    }

    @Override
    @Transactional
    public void save(OfficeFullView officeFullView) {
        dao.save(mapperFacade.map(officeFullView, Office.class));
    }

    @Override
    @Transactional
    public OfficeListView list(Integer orgId, String name, String phone, Boolean isActive) {
        Office office = dao.list(orgId, name, phone, isActive);
        return mapperFacade.map(office, OfficeListView.class);
    }
}

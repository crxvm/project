package com.example.project.service.office;

import com.example.project.dao.office.OfficeDao;
import com.example.project.model.Office;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.office.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public OfficeView getById(Long id) {
        Office office = dao.getById(id);
        return mapperFacade.map(office, OfficeView.class);
    }

    @Override
    @Transactional
    public void update(OfficeUpdateView officeUpdateView) {
        dao.update(mapperFacade.map(officeUpdateView, Office.class));
    }

    @Override
    @Transactional
    public void save(OfficeSaveView officeSaveView) {
        dao.save(mapperFacade.map(officeSaveView, Office.class));
    }

    @Override
    @Transactional
    public List<OfficeListOutView> list(OfficeListInView officeListInView) {
        List<Office> office = dao.list(officeListInView.orgId, officeListInView.name,officeListInView.phone,officeListInView.isActive);
        return mapperFacade.mapAsList(office, OfficeListOutView.class);
    }
}

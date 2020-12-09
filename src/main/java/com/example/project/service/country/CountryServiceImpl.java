package com.example.project.service.country;

import com.example.project.dao.country.CountryDao;
import com.example.project.model.Country;
import com.example.project.model.mapper.MapperFacade;
import com.example.project.view.CountryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryDao dao;
    private final MapperFacade mapperFacade;
    @Autowired
    public CountryServiceImpl(CountryDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryView> countries() {
       List<Country> all = dao.all();
       return mapperFacade.mapAsList(all, CountryView.class);
    }
}

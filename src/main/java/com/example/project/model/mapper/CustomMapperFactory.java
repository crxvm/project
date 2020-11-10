package com.example.project.model.mapper;

import com.example.project.model.User;
import com.example.project.view.UserView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

/**
 * Фабрика для создания MapperFactory.
 * При необходимости можно добавить кастомные мапперы
 */
@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    @Override
    public MapperFactory getObject() {
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
        mapperFactory.classMap(User.class, UserView.class )
                .byDefault()
                .field("userDocument.document.docName", "docName")
                .field("userDocument.docNumber", "docNumber")
                .field("userDocument.docDate", "docDate")
                .field("country.citizenshipName", "citizenshipName")
                .register();
        return mapperFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

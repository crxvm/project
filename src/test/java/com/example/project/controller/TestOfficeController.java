package com.example.project.controller;

import com.example.project.model.Office;
import com.example.project.service.office.OfficeService;
import com.example.project.view.ResultView;
import com.example.project.view.office.*;
import com.example.project.view.wrapper.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Тесты для контроллера OfficeController
 * @see com.example.project.controller.office.OfficeController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestOfficeController {
    private final String HOST = "http://localhost";
    private final String API_PATH = "/api/office/";
    private final HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    private final RestTemplate rest = new RestTemplate();

    @Autowired
    OfficeService officeService;
    @Autowired
    EntityManager em;

    /**
     * Тестирует метод getById(), для запроса api/office/{id:[\d]+}}
     * @see OfficeView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testGetById() throws URISyntaxException {
        Integer id = 1;
        URI uri = new URI(HOST + ":" + port + API_PATH + id);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Data<OfficeView>> response
                = rest.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    /**
     * Тестирует метод save(), для запроса api/office/save
     * @throws URISyntaxException исколючение если не найдена ссылка
     * @see OfficeSaveView
     */
    @Test
    public void testSave() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "save");
        OfficeSaveView view = new OfficeSaveView();
        view.orgId = 1L;
        view.address = "TestAddress";
        view.name = "TestName";
        view.phone = "TestPhone";
        view.isActive = true;

        HttpEntity<OfficeSaveView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(response.getStatusCodeValue(), 200);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteria = cb.createQuery(Office.class);
        Root<Office> office = criteria.from(Office.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(office.get("name"), view.name));
        predicates.add(cb.like(office.get("address"), view.address));
        predicates.add(cb.equal(office.get("orgId"), view.orgId));
        predicates.add(cb.like(office.get("phone"), view.phone));
        predicates.add(cb.equal(office.get("isActive"), view.isActive));

        TypedQuery<Office> query = em.createQuery(criteria.where(predicates.toArray(new Predicate[predicates.size()])));
        Assert.assertNotNull(query.getResultList());
    }

    /**
     * Теструет метод update() для запроса api/office/update
     * @see OfficeUpdateView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testUpdate() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "update");
        OfficeUpdateView view = new OfficeUpdateView();
        view.id = 1L;
        view.address = "TestAddress1";
        view.name = "TestName1";
        view.phone = "TestPhone1";
        view.isActive = true;

        HttpEntity<OfficeUpdateView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(response.getStatusCodeValue(), 200);

        OfficeView test = officeService.getById(view.id);
        Assert.assertEquals(test.id, view.id);
        Assert.assertEquals(test.address, view.address);
        Assert.assertEquals(test.name, view.name);
        Assert.assertEquals(test.phone, view.phone);
        Assert.assertEquals(test.isActive, test.isActive);

    }

    /**
     * Тестирует метод list() для запроса api/office/list
     * @see OfficeListInView
     * @see OfficeListOutView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void  testList() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "list");
        OfficeListInView filter = new OfficeListInView();
        filter.isActive = true;
        filter.name = "TestName1";
        filter.orgId = 1;
        filter.phone = "TestPhone1";

        HttpEntity<OfficeListInView> httpEntity = new HttpEntity<>(filter, headers);
        ResponseEntity<Data<List<OfficeListOutView>>> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());
        List<OfficeListOutView> view = response.getBody().getData();

        if(!view.isEmpty()) {
            Assert.assertEquals(view.size(), 1);
        }
        else {
            Assert.fail();
        }
    }
}

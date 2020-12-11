package com.example.project.controller;

import com.example.project.model.Organization;
import com.example.project.service.organization.OrganizationService;
import com.example.project.view.ResultView;
import com.example.project.view.organization.OrganizationListInView;
import com.example.project.view.organization.OrganizationListOutView;
import com.example.project.view.organization.OrganizationSaveView;
import com.example.project.view.organization.OrganizationView;
import com.example.project.view.wrapper.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
 * Тесты для контроллера OrganizationController
 * @see com.example.project.controller.organization.OrganizationController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestOrganizationController {
    private final String HOST = "http://localhost";
    private final String API_PATH = "/api/organization/";
    private final HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    private final RestTemplate rest = new RestTemplate();

    @Autowired
    OrganizationService organizationService;
    @Autowired
    EntityManager em;

    /**
     * Тестирует метод getById(), для запроса api/organization/{id:[\d]+}}
     * @see OrganizationView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testGetById() throws URISyntaxException {
        Integer id = 1;
        URI uri = new URI(HOST + ":" + port + API_PATH + id);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Data<OrganizationView>> response
                = rest.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());

    }

    /**
     * Тестирует метод save(), для запроса api/organization/save
     * @see OrganizationSaveView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testSave() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "save");
        OrganizationSaveView view = new OrganizationSaveView();
        view.fullName = "TestFullName";
        view.address = "TestAddress";
        view.name =  "TestName";
        view.inn = "TestInn";
        view.isActive = true;
        view.kpp = "TestKpp";
        view.phone = "TestPhone";

        HttpEntity<OrganizationSaveView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> responseEntity = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteria = cb.createQuery(Organization.class);
        Root<Organization> organization = criteria.from(Organization.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(organization.get("fullName"), view.fullName));
        predicates.add(cb.like(organization.get("name"), view.name));
        predicates.add(cb.like(organization.get("address"), view.address));
        predicates.add(cb.like(organization.get("inn"), view.inn));
        predicates.add(cb.like(organization.get("kpp"), view.kpp));
        predicates.add(cb.like(organization.get("phone"), view.phone));
        predicates.add(cb.equal(organization.get("isActive"), view.isActive));

        TypedQuery<Organization> query = em.createQuery(criteria.where(predicates.toArray(new Predicate[predicates.size()])));
        Assert.assertNotNull(query.getResultList());
    }

    /**
     * Тестирует метод update(), для запроса api/organization/update
     * @see OrganizationView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testUpdate() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "update");
        OrganizationView view = new OrganizationView();
        view.id = 4L;
        view.fullName = "TestFullName1";
        view.address = "TestAddress1";
        view.name =  "TestName1";
        view.inn = "TestInn1";
        view.isActive = true;
        view.kpp = "TestKpp1";
        view.phone = "TestPhone1";

        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> responseEntity = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        OrganizationView test =  organizationService.getById(view.id);
        Assert.assertEquals(view.id, test.id);
        Assert.assertEquals(view.address, test.address);
        Assert.assertEquals(view.fullName, test.fullName);
        Assert.assertEquals(view.inn, test.inn);
        Assert.assertEquals(view.kpp, test.kpp);
        Assert.assertEquals(view.isActive, test.isActive);
        Assert.assertEquals(view.phone, test.phone);
        Assert.assertEquals(view.name, test.name);

    }

    /**
     * Тестирует метод list() для запроса api/organization/list
     * @see OrganizationListInView
     * @see OrganizationListOutView
     * @see Data
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testList() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "list");
        OrganizationListInView filter = new OrganizationListInView();
        filter.inn = "TestInn1";
        filter.isActive = true;
        filter.name = "TestName1";

        HttpEntity<OrganizationListInView> httpEntity = new HttpEntity<>(filter, headers);
        ResponseEntity<Data<List<OrganizationListOutView>>> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());
        List<OrganizationListOutView> view = response.getBody().getData();

        if(!view.isEmpty()) {
            Assert.assertEquals(view.size(), 1);
        }
        else {
            Assert.fail();
        }
    }
}

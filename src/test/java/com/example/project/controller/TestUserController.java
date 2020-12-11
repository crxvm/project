package com.example.project.controller;


import com.example.project.model.User;
import com.example.project.service.user.UserService;
import com.example.project.view.ResultView;
import com.example.project.view.organization.OrganizationListInView;
import com.example.project.view.organization.OrganizationListOutView;
import com.example.project.view.organization.OrganizationSaveView;
import com.example.project.view.organization.OrganizationView;
import com.example.project.view.user.*;
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
import javax.persistence.criteria.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Тесты для контроллера UserController
 * @see org.hibernate.usertype.UserCollectionType
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestUserController {
    private final String HOST = "http://localhost";
    private final String API_PATH = "/api/user/";
    private final HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    private final RestTemplate rest = new RestTemplate();

    @Autowired
    EntityManager em;
    @Autowired
    UserService userService;

    /**
     * Тестирует метод getById(), для запроса api/user/{id:[\d]+}}
     * @see UserView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testGetById() throws URISyntaxException {
        Integer id = 1;
        URI uri = new URI(HOST + ":" + port + API_PATH + id);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Data<UserView>> response
                = rest.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    /**
     * Тестирует метод save(), для запроса api/user/save
     * @see UserSaveView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testSave() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "save");
        UserSaveView view = new UserSaveView();
        view.officeId = 1;
        view.firstName = "TestFName";
        view.secondName = "TestSName";
        view.middleName = "TestMName";
        view.position = "TestPosition";
        view.phone = "TestPhone";
        view.docCode = "1";
        view.docName = "Passport";
        view.docNumber = "TestNumber";
        view.citizenshipCode = "643";
        view.docDate = null;
        view.isIdentified = true;

        HttpEntity<UserSaveView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> responseEntity = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        Join country = user.join("country");
        Join userDocument = user.join("userDocument");
        Join document = userDocument.join("document");

        List<Predicate> list = new ArrayList<>();
        list.add(cb.like(user.get("firstName"), view.firstName));
        list.add(cb.like(user.get("secondName"), view.secondName));
        list.add(cb.like(user.get("middleName"), view.middleName));
        list.add(cb.equal(user.get("officeId"), view.officeId));
        list.add(cb.like(user.get("position"), view.position));
        list.add(cb.like(user.get("phone"), view.phone));
        list.add(cb.like(document.get("docCode"), view.docCode));
        list.add(cb.like(document.get("docName"), view.docName));
        list.add(cb.like(userDocument.get("docNumber"), view.docNumber));
        list.add(cb.like(country.get("citizenshipCode"), view.citizenshipCode));

        TypedQuery<User> query = em.createQuery(criteria.where(list.toArray(new Predicate[list.size()])));
        Assert.assertNotNull(query.getResultList());
    }

    /**
     * Тестирует метод update(), для запроса api/user/update
     * @see UserUpdateView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testUpdate() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "update");
        UserUpdateView view = new UserUpdateView();
        view.id = 1L;
        view.officeId = 1;
        view.firstName = "TestFName1";
        view.secondName = "TestSName1";
        view.middleName = "TestMName1";
        view.position = "TestPosition1";
        view.phone = "TestPhone1";
        view.docName = "Driver License";
        view.docNumber = "Test1";
        view.citizenshipCode = "392";
        view.docDate = null;
        view.isIdentified = true;

        HttpEntity<UserUpdateView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> responseEntity = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        UserView test = userService.getById(view.id);
        Assert.assertEquals(view.id, test.id);
        Assert.assertEquals(test.firstName, view.firstName);
        Assert.assertEquals(test.secondName, view.secondName);
        Assert.assertEquals(test.middleName, view.middleName);
        Assert.assertEquals(test.phone, view.phone);
        Assert.assertEquals(test.position, view.position);
        Assert.assertEquals(test.docName, view.docName);
        Assert.assertEquals(test.docNumber, view.docNumber);
        Assert.assertEquals(test.isIdentified, view.isIdentified);
        Assert.assertEquals(test.officeId, view.officeId);
    }

    /**
     * Тестирует метод list() для запроса api/office/list
     * @see UserListInView
     * @see UserListOutView
     * @see Data
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testList() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "list");
        UserListInView filter = new UserListInView();
        filter.officeId = 1;
        filter.firstName = "TestFName1";
        filter.secondName = "TestSName1";
        filter.middleName = "TestMName1";
        filter.position = "TestPosition1";
        filter.citizenshipCode = "392";
        filter.docCode = "2";

        HttpEntity<UserListInView> httpEntity = new HttpEntity<>(filter, headers);
        ResponseEntity<Data<List<UserListOutView>>> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());
        List<UserListOutView> view = response.getBody().getData();

        if(!view.isEmpty()) {
            Assert.assertEquals(view.size(), 1);
        }
        else {
            Assert.fail();
        }
    }
}


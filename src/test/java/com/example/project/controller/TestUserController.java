package com.example.project.controller;

import com.example.project.view.ResultView;
import com.example.project.view.user.*;
import com.example.project.view.wrapper.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

/**
 * Тесты для контроллера UserController
 * @see org.hibernate.usertype.UserCollectionType
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestUserController {
    private final String API_PATH = "/api/user/";
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    private TestRestTemplate rest;

    /**
     * Тестирует метод getById(), для запроса api/user/{id:[\d]+}}
     * @see UserView
     */
    @Test
    public void testGetById(){
        Integer id = 1;

        String uri = API_PATH + id;

        ResponseEntity<Data<UserView>> response
                = rest.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Data<UserView>>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    /**
     * Тестирует метод save(), для запроса api/user/save
     * @see UserSaveView
     */
    @Test
    public void testSave()  {

        String uri = API_PATH + "save";
        UserSaveView view = new UserSaveView();
        view.officeId = 1;
        view.firstName = "SaveFName";
        view.secondName = "SaveSName";
        view.middleName = "SaveMName";
        view.position = "SavePosition";
        view.phone = "SavePhone";
        view.docCode = "1";
        view.docName = "Passport";
        view.docNumber = "SaveNumber";
        view.citizenshipCode = "643";
        view.docDate = null;
        view.isIdentified = true;

        HttpEntity<UserSaveView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> responseEntity = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResultView>(){});
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        String testListUri = API_PATH + "list";
        UserListInView filter = new UserListInView();
        filter.docCode = view.docCode;
        filter.citizenshipCode = view.citizenshipCode;
        filter.firstName = view.firstName;
        filter.secondName = view.secondName;
        filter.middleName = view.middleName;
        filter.position = view.position;
        filter.officeId = view.officeId;

        HttpEntity<UserListInView> httpEntityList = new HttpEntity<>(filter, headers);
        UserListOutView outView = rest.exchange(testListUri, HttpMethod.POST, httpEntityList, new ParameterizedTypeReference<Data<List<UserListOutView>>>(){}).getBody().getData().get(0);
        Long id = outView.id;

        String testIdUri = API_PATH + id;
        UserView test = rest.exchange(testIdUri, HttpMethod.GET, null, new ParameterizedTypeReference<Data<UserView>>(){}).getBody().getData();

        Assert.assertEquals(view.officeId, test.officeId);
        Assert.assertEquals(view.firstName, test.firstName);
        Assert.assertEquals(view.secondName, test.secondName);
        Assert.assertEquals(view.middleName, test.middleName);
        Assert.assertEquals(view.position, test.position);
        Assert.assertEquals(view.phone, test.phone);
        Assert.assertEquals(view.docName, view.docName);
        Assert.assertEquals(view.docNumber, test.docNumber);
        Assert.assertEquals(view.docDate, view.docDate);
        Assert.assertEquals(view.isIdentified, view.isIdentified);

    }

    /**
     * Тестирует метод update(), для запроса api/user/update
     * @see UserUpdateView
     */
    @Test
    public void testUpdate() {
        //URI uri = new URI(HOST + ":" + port + API_PATH + "update");
        String uri = API_PATH + "update";
        UserUpdateView view = new UserUpdateView();
        view.id = 1L;
        view.officeId = 1;
        view.firstName = "UpdateFName";
        view.secondName = "UpdateSName";
        view.middleName = "UpdateMName";
        view.position = "UpdatePosition";
        view.phone = "UpdatePhone";
        view.docName = "Driver License";
        view.docNumber = "Update";
        view.citizenshipCode = "392";
        view.docDate = null;
        view.isIdentified = true;

        HttpEntity<UserUpdateView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> responseEntity = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResultView>() {
        });

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        HttpEntity<String> httpEntityId = new HttpEntity<>(headers);
        //URI uriId = new URI(HOST + ":" + port + API_PATH + view.id);
        String uriId = API_PATH + view.id;
        UserView test = rest.exchange(uriId, HttpMethod.GET, httpEntityId, new ParameterizedTypeReference<Data<UserView>>() {
        }).getBody().getData();
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
     */
    @Test
    public void testList()  {

        String uri = API_PATH + "list";
        UserListInView filter = new UserListInView();
        filter.officeId = 1;
        filter.firstName = "TestFName";
        filter.secondName = "TestSName";
        filter.middleName = "TestMName";
        filter.position = "TestPosition";
        filter.citizenshipCode = "392";
        filter.docCode = "2";

        HttpEntity<UserListInView> httpEntity = new HttpEntity<>(filter, headers);
        ResponseEntity<Data<List<UserListOutView>>> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Data<List<UserListOutView>>>(){});
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


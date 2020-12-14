package com.example.project.controller;


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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

/**
 * Тесты для контроллера OrganizationController
 * @see com.example.project.controller.organization.OrganizationController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestOrganizationController {
    private final String API_PATH = "/api/organization/";
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    private TestRestTemplate rest;

    /**
     * Тестирует метод getById(), для запроса api/organization/{id:[\d]+}}
     * @see OrganizationView
     */
    @Test
    public void testGetById()  {
        Integer id = 1;
        String uri = API_PATH + id;

        ResponseEntity<Data<OrganizationView>> response
                = rest.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Data<OrganizationView>>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());

    }

    /**
     * Тестирует метод save(), для запроса api/organization/save
     * @see OrganizationSaveView
     */
    @Test
    public void testSave()  {
        String uri = API_PATH + "save";
        OrganizationSaveView view = new OrganizationSaveView();
        view.fullName = "SaveFullName";
        view.address = "SaveAddress";
        view.name =  "SaveName";
        view.inn = "SaveInn";
        view.isActive = true;
        view.kpp = "SaveKpp";
        view.phone = "SavePhone";

        HttpEntity<OrganizationSaveView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> responseEntity = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResultView>(){});

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        String testListUri = API_PATH + "list";
        OrganizationListInView filter = new OrganizationListInView();
        filter.inn = view.inn;
        filter.isActive = true;
        filter.name = view.name;

        HttpEntity<OrganizationListInView> httpEntityList = new HttpEntity<>(filter, headers);
        OrganizationListOutView outView = rest.exchange(testListUri, HttpMethod.POST, httpEntityList, new ParameterizedTypeReference<Data<List<OrganizationListOutView>>>(){}).getBody().getData().get(0);
        Long id = outView.id;

        String testIdUri = API_PATH + id;
        OrganizationView test = rest.exchange(testIdUri, HttpMethod.GET, null, new ParameterizedTypeReference<Data<OrganizationView>>(){}).getBody().getData();
        Assert.assertEquals(view.address, test.address);
        Assert.assertEquals(view.fullName, test.fullName);
        Assert.assertEquals(view.name, test.name);
        Assert.assertEquals(view.phone, test.phone);
        Assert.assertEquals(view.inn, test.inn);
        Assert.assertEquals(view.kpp, test.kpp);
        Assert.assertEquals(view.isActive, test.isActive);
    }

    /**
     * Тестирует метод update(), для запроса api/organization/update
     * @see OrganizationView
     */
    @Test
    public void testUpdate()  {
        String uri = API_PATH + "update";
        OrganizationView view = new OrganizationView();
        view.id = 1L;
        view.fullName = "UFullName";
        view.address = "UAddress";
        view.name =  "UName";
        view.inn = "UInn";
        view.isActive = true;
        view.kpp = "UKpp";
        view.phone = "UPhone";

        HttpEntity<OrganizationView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> responseEntity = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResultView>(){});

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        String testUri = API_PATH + view.id;
        OrganizationView test = rest.exchange(testUri, HttpMethod.GET, null, new ParameterizedTypeReference<Data<OrganizationView>>(){}).getBody().getData();
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
     */
    @Test
    public void testList()  {

        String uri = API_PATH + "list";
        OrganizationListInView filter = new OrganizationListInView();
        filter.inn = "TestInn";
        filter.isActive = true;
        filter.name = "TestName";

        HttpEntity<OrganizationListInView> httpEntity = new HttpEntity<>(filter, headers);
        ResponseEntity<Data<List<OrganizationListOutView>>> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Data<List<OrganizationListOutView>>>(){});
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

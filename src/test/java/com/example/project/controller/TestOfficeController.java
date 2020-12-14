package com.example.project.controller;

import com.example.project.view.ResultView;
import com.example.project.view.office.*;
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
 * Тесты для контроллера OfficeController
 * @see com.example.project.controller.office.OfficeController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestOfficeController {

    private final String API_PATH = "/api/office/";
    private final HttpHeaders headers = new HttpHeaders();
    @Autowired
    private TestRestTemplate rest;


    /**
     * Тестирует метод getById(), для запроса api/office/{id:[\d]+}}
     * @see OfficeView
     */
    @Test
    public void testGetById()  {
        Integer id = 1;
        String uri = API_PATH + id;

        ResponseEntity<Data<OfficeView>> response
                = rest.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Data<OfficeView>>(){});
        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    /**
     * Тестирует метод save(), для запроса api/office/save
     * @see OfficeSaveView
     */
    @Test
    public void testSave()  {
        String uri = API_PATH + "save";
        OfficeSaveView view = new OfficeSaveView();
        view.orgId = 1L;
        view.address = "SaveAddress";
        view.name = "SaveName";
        view.phone = "SavePhone";
        view.isActive = true;

        HttpEntity<OfficeSaveView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResultView>(){});
        Assert.assertEquals(response.getStatusCodeValue(), 200);

        String testListUri = API_PATH + "list";
        OfficeListInView filter = new OfficeListInView();
        filter.isActive = true;
        filter.name = view.name;
        filter.orgId = view.orgId.intValue();
        filter.phone = view.phone;

        HttpEntity<OfficeListInView> httpEntityList = new HttpEntity<>(filter, headers);
        OfficeListOutView outView =  rest.exchange(testListUri, HttpMethod.POST, httpEntityList, new ParameterizedTypeReference<Data<List<OfficeListOutView>>>(){}).getBody().getData().get(0);
        Long id = outView.id.longValue();

        String testIdUri = API_PATH + id;
        OfficeView test = rest.exchange(testIdUri, HttpMethod.GET, null, new ParameterizedTypeReference<Data<OfficeView>>(){}).getBody().getData();
        Assert.assertEquals(test.id.longValue(), outView.id.longValue());
        Assert.assertEquals(test.address, view.address);
        Assert.assertEquals(test.name, view.name);
        Assert.assertEquals(test.phone, view.phone);
        Assert.assertEquals(test.isActive, test.isActive);
    }

    /**
     * Теструет метод update() для запроса api/office/update
     * @see OfficeUpdateView
     */
    @Test
    public void testUpdate()  {
        String uri = API_PATH + "update";
        OfficeUpdateView view = new OfficeUpdateView();
        view.id = 1L;
        view.address = "UpdateAddress";
        view.name = "UpdateName";
        view.phone = "UpdatePhone";
        view.isActive = true;

        HttpEntity<OfficeUpdateView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResultView>(){});
        Assert.assertEquals(response.getStatusCodeValue(), 200);

        String testUri = API_PATH + view.id;
        OfficeView test = rest.exchange(testUri, HttpMethod.GET, null, new ParameterizedTypeReference<Data<OfficeView>>(){}).getBody().getData();
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
     */
    @Test
    public void  testList()  {

        String uri = API_PATH + "list";
        OfficeListInView filter = new OfficeListInView();
        filter.isActive = true;
        filter.name = "TestName";
        filter.orgId = 1;
        filter.phone = "TestPhone";

        HttpEntity<OfficeListInView> httpEntity = new HttpEntity<>(filter, headers);
        ResponseEntity<Data<List<OfficeListOutView>>> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<Data<List<OfficeListOutView>>>(){});
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

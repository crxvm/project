package com.example.project.controller;

import com.example.project.view.ResultView;
import com.example.project.view.office.*;
import com.example.project.view.wrapper.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.net.URISyntaxException;
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


    /**
     * Тестирует метод getById(), для запроса api/office/{id:[\d]+}}
     * @see OfficeView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testGetById() throws URISyntaxException {
        Integer id = 1;
        URI uri = new URI(HOST + ":" + port + API_PATH + id);

        ResponseEntity<Data<OfficeView>> response
                = rest.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
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
        view.address = "SaveAddress";
        view.name = "SaveName";
        view.phone = "SavePhone";
        view.isActive = true;

        HttpEntity<OfficeSaveView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(response.getStatusCodeValue(), 200);

        URI testListUri = new URI(HOST + ":" + port + API_PATH + "list");
        OfficeListInView filter = new OfficeListInView();
        filter.isActive = true;
        filter.name = view.name;
        filter.orgId = view.orgId.intValue();
        filter.phone = view.phone;

        HttpEntity<OfficeListInView> httpEntityList = new HttpEntity<>(filter, headers);
        OfficeListOutView outView =  rest.exchange(testListUri, HttpMethod.POST, httpEntityList, new ParameterizedTypeReference<Data<List<OfficeListOutView>>>(){}).getBody().getData().get(0);
        Long id = outView.id.longValue();


        URI testIdUri = new URI(HOST + ":" + port + API_PATH + id);
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
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testUpdate() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "update");
        OfficeUpdateView view = new OfficeUpdateView();
        view.id = 1L;
        view.address = "UpdateAddress";
        view.name = "UpdateName";
        view.phone = "UpdatePhone";
        view.isActive = true;

        HttpEntity<OfficeUpdateView> httpEntity = new HttpEntity<>(view, headers);
        ResponseEntity<ResultView> response
                = rest.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>(){});
        Assert.assertEquals(response.getStatusCodeValue(), 200);

        URI testUri = new URI(HOST + ":" + port + API_PATH + view.id);
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
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void  testList() throws URISyntaxException {
        URI uri = new URI(HOST + ":" + port + API_PATH + "list");
        OfficeListInView filter = new OfficeListInView();
        filter.isActive = true;
        filter.name = "TestName";
        filter.orgId = 1;
        filter.phone = "TestPhone";

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

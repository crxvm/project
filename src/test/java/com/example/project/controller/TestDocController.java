package com.example.project.controller;

import com.example.project.view.DocumentView;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Тесты для контроллера DocumentController
 * @see com.example.project.controller.doc.DocumentController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestDocController {
    private final String API_PATH = "/api/docs";
    @Autowired
    private TestRestTemplate rest;

    /**
     * Тестирует метод documents для запроса api/docs
     * @see DocumentView
     */
    @Test
    public void testGetDocs()  {


        ResponseEntity<Data<List<DocumentView>>> response
                = rest.exchange(API_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Data<List<DocumentView>>>() {
                });

        Data<List<DocumentView>> data = response.getBody();
        Assert.assertNotNull(data);
        List<DocumentView> documents = data.getData();

        assertEquals(200, response.getStatusCodeValue());
        if (!documents.isEmpty()) {
            assertEquals(2, documents.size());
        } else {
            fail();
        }
    }
}

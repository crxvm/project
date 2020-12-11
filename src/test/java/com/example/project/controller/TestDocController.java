package com.example.project.controller;

import com.example.project.view.DocumentView;
import com.example.project.view.wrapper.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;
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
    private final String HOST = "http://localhost";
    private final String API_PATH = "/api/docs";
    @LocalServerPort
    private int port;
    private final RestTemplate rest = new RestTemplate();

    /**
     * Тестирует метод documents для запроса api/docs
     * @see DocumentView
     * @throws URISyntaxException исколючение если не найдена ссылка
     */
    @Test
    public void testGetDocs() throws URISyntaxException {
        final URI uri = new URI(HOST + ":" + port + API_PATH);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Data<List<DocumentView>>> response
                = rest.exchange(uri,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {
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

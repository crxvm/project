package com.example.project.controller;

import com.example.project.view.CountryView;
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

import static org.junit.Assert.*;

/**
 * Тест для контроллера CountryController
 * @see com.example.project.controller.country.CountryController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestCountryController {
    private final String HOST = "http://localhost";
    private final String API_PATH = "/api/countries";
    private final HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;
    private final RestTemplate rest = new RestTemplate();

    /**
     * Тестирует метод countries для запроса api/countries
     * @throws URISyntaxException исколючение если не найдена ссылка
     * @see CountryView
     */
    @Test
    public void testGetAllCountries() throws URISyntaxException {
        final URI URI_LIST = new URI(HOST + ":" + port + API_PATH);

        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Data<List<CountryView>>> response
                = rest.exchange(URI_LIST,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });

        Data<List<CountryView>> data = response.getBody();
        Assert.assertNotNull(data);
        List<CountryView> countries = data.getData();

        assertEquals(200, response.getStatusCodeValue());
        if (!countries.isEmpty()) {
            assertEquals(5, countries.size());
        } else {
            fail();
        }
    }
}
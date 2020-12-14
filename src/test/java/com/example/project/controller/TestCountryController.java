package com.example.project.controller;

import com.example.project.view.CountryView;
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

import static org.junit.Assert.*;

/**
 * Тест для контроллера CountryController
 * @see com.example.project.controller.country.CountryController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestCountryController {

    private final String API_PATH = "/api/countries";
    @Autowired
    private TestRestTemplate rest;
    /**
     * Тестирует метод countries для запроса api/countries
     * @see CountryView
     */
    @Test
    public void testGetAllCountries()  {

        ResponseEntity<Data<List<CountryView>>> response
                = rest.exchange(API_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Data<List<CountryView>>>() {
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
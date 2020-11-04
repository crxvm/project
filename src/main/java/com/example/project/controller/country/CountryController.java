package com.example.project.controller.country;

import com.example.project.service.country.CountryService;
import com.example.project.view.CountryView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/api/countries", produces = APPLICATION_JSON_VALUE)
@Api(value = "CountryController", description = "=description")
public class CountryController {
    private final CountryService countryService;
    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @ApiOperation(value = "Получить информацию о странах", httpMethod = "GET")
    @GetMapping(value = "/")
    private List<CountryView> countries() {
        return countryService.countries();
    }
}

package com.example.project.controller.organization;

import com.example.project.service.organization.OrganizationService;
import com.example.project.view.OrganizationFullView;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "OrganizationController", description = "Информация об организациях")
@RestController
@RequestMapping(value = "/api/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    @GetMapping("/{id}")
    public OrganizationFullView getById(@PathVariable("id") Long id){
        return organizationService.getById(id);
    }

}

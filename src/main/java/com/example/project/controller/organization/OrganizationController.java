package com.example.project.controller.organization;

import com.example.project.service.organization.OrganizationService;
import com.example.project.view.OrganizationFullView;
import com.example.project.view.OrganizationListView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "OrganizationController", description = "Информация об организациях")
@RestController
@RequestMapping(value = "/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    @GetMapping("/{id}")
    public OrganizationFullView getById(@PathVariable("id") Long id){
        return organizationService.getById(id);
    }
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void save(@RequestBody OrganizationFullView organizationFullView) {
        organizationService.save(organizationFullView);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public void update(@RequestBody OrganizationFullView organizationFullView) {
        organizationService.update(organizationFullView);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/list")
    public OrganizationListView list(@RequestBody OrganizationFullView organizationFullView) {
        return organizationService.list(organizationFullView.name, organizationFullView.inn, organizationFullView.is_Active);
    }
}

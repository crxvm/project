package com.example.project.controller.organization;

import com.example.project.service.organization.OrganizationService;
import com.example.project.view.organization.OrganizationListInView;
import com.example.project.view.organization.OrganizationSaveView;
import com.example.project.view.organization.OrganizationView;
import com.example.project.view.organization.OrganizationListOutView;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "OrganizationController", description = "Информация об организациях")
@RestController
@RequestMapping(value = "/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final Validator validator;

    @Autowired
    public OrganizationController(OrganizationService organizationService, @Qualifier("mvcValidator") Validator validator) {
        this.validator = validator;
        this.organizationService = organizationService;

    }
    @GetMapping("/{id}")
    public OrganizationView getById(@PathVariable("id") Long id){
        return organizationService.getById(id);
    }

    @PostMapping("/save")
    public void save( @RequestBody OrganizationSaveView view, BindingResult bindingResult
    ) {
        organizationService.save(view);
    }

    @PostMapping("/update")
    public void update(@RequestBody OrganizationView organizationView) {
        organizationService.update(organizationView);
    }

    @PostMapping("/list")
    public List<OrganizationListOutView> list(@RequestBody OrganizationListInView view) {
        return organizationService.list(view);//view.name, view.inn, view.isActive);
    }
}

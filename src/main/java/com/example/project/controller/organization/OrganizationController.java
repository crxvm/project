package com.example.project.controller.organization;

import com.example.project.service.organization.OrganizationService;
import com.example.project.view.ResultView;
import com.example.project.view.organization.OrganizationListInView;
import com.example.project.view.organization.OrganizationSaveView;
import com.example.project.view.organization.OrganizationView;
import com.example.project.view.organization.OrganizationListOutView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;

    }
    @GetMapping("/{id}")
    public OrganizationView getById(@PathVariable("id") Long id){
        OrganizationView organizationView  = organizationService.getById(id);
        if (organizationView == null) {
            throw new NoResultException("cannot find organization with id = " + id.toString());
        }
        return organizationView;
    }

    @PostMapping("/save")
    @ResponseBody
    public ResultView save(@Valid @RequestBody OrganizationSaveView view
    ) {
        organizationService.save(view);
        return new ResultView();
    }

    @PostMapping("/update")
    @ResponseBody
    public ResultView update(@Valid @RequestBody OrganizationView organizationView) {
        organizationService.update(organizationView);
        return new ResultView();
    }

    @PostMapping("/list")
    @ResponseBody
    public List<OrganizationListOutView> list(@Valid @RequestBody OrganizationListInView view) {
        return organizationService.list(view);
    }
}

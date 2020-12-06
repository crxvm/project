package com.example.project.controller.organization;

import com.example.project.service.organization.OrganizationService;
import com.example.project.view.ResultView;
import com.example.project.view.organization.OrganizationListInView;
import com.example.project.view.organization.OrganizationSaveView;
import com.example.project.view.organization.OrganizationView;
import com.example.project.view.organization.OrganizationListOutView;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
    public OrganizationView getById(@PathVariable("id") Long id){
        return organizationService.getById(id);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResultView save(@Valid @RequestBody OrganizationSaveView view, BindingResult bindingResult
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

package com.example.project.controller.office;

import com.example.project.service.office.OfficeService;
import com.example.project.view.OfficeFullView;
import com.example.project.view.OfficeListView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "OfficeController")
@RestController
@RequestMapping(value = "/api/office")
public class OfficeController {
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }
    @PostMapping(value = "/list")
    public OfficeListView list(@RequestBody OfficeFullView officeFullView) {
        return officeService.list(officeFullView.orgId, officeFullView.name,
                officeFullView.phone, officeFullView.isActive);
    }

    @GetMapping(value ="/{id}" )
    public OfficeFullView getById(@PathVariable("id") Long id) {
        return officeService.getById(id);
    }

    @PostMapping(value = "/save")
    public void save(@RequestBody OfficeFullView officeFullView) {
        officeService.save(officeFullView);
    }

    @PostMapping(value = "/update")
    public void update(@RequestBody OfficeFullView officeFullView) {
        officeService.update(officeFullView);
    }
}

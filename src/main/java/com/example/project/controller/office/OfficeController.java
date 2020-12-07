package com.example.project.controller.office;

import com.example.project.service.office.OfficeService;
import com.example.project.view.ResultView;
import com.example.project.view.office.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.List;

@Api(value = "OfficeController")
@RestController
@RequestMapping(value = "/api/office")
public class OfficeController {
    private final OfficeService officeService;


    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }
    @ResponseBody
    @PostMapping(value = "/list")
    public List<OfficeListOutView> list(@Valid @RequestBody OfficeListInView officeListInView) {
        return officeService.list(officeListInView);
    }

    @GetMapping(value ="/{id}" )
    public OfficeView getById(@PathVariable("id") Long id) {
        OfficeView officeView = officeService.getById(id);
        if (officeView == null) {
            throw new NoResultException("cannot find office with id = " + id);
        }
        return officeView;
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public ResultView save(@Valid @RequestBody OfficeSaveView officeSaveView) {
        officeService.save(officeSaveView);
        return new ResultView();
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResultView update(@Valid @RequestBody OfficeUpdateView officeUpdateView) {
        officeService.update(officeUpdateView);
        return new ResultView();
    }
}

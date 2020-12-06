package com.example.project.controller.office;

import com.example.project.service.office.OfficeService;
import com.example.project.view.ResultView;
import com.example.project.view.office.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping(value = "/list")
    public List<OfficeListOutView> list(@RequestBody OfficeListInView officeListInView) {
        return officeService.list(officeListInView);
    }

    @GetMapping(value ="/{id}" )
    public OfficeView getById(@PathVariable("id") Long id) {
        return officeService.getById(id);
    }

    @PostMapping(value = "/save")
    public ResultView save(@RequestBody OfficeSaveView officeSaveView) {
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

package com.example.project.controller.office;

import com.example.project.service.office.OfficeService;
import com.example.project.view.ResultView;
import com.example.project.view.office.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.List;

/**
 *Контроллер для управления данными об офисах
 */
@RestController
@RequestMapping(value = "/api/office")
public class OfficeController {
    private final OfficeService officeService;


    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Получить список офисов по указанным параметрам в теле запроса
     * @param officeListInView отображение для получения списка офисов {@link OfficeListInView}
     * @return список объектов {@link OfficeListOutView}
     */
    @ResponseBody
    @PostMapping(value = "/list")
    public List<OfficeListOutView> list(@Valid @RequestBody OfficeListInView officeListInView) {
        return officeService.list(officeListInView);
    }

    /**
     * Возвращает офис по id
     * @param id - уникальный идентификатор
     * @return объект для отображения данных об офисе {@link OfficeView}
     */
    @GetMapping(value ="/{id}" )
    public OfficeView getById(@PathVariable("id") Long id) {
        OfficeView officeView = officeService.getById(id);
        if (officeView == null) {
            throw new NoResultException("cannot find office with id = " + id);
        }
        return officeView;
    }

    /**
     * Сохраняет новый офис
     * @param officeSaveView отображение для сохранения офиса {@link OfficeSaveView}
     * @return возвращается результат выполнения, пример: result = "success"
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public ResultView save(@Valid @RequestBody OfficeSaveView officeSaveView) {
        officeService.save(officeSaveView);
        return new ResultView();
    }

    /**
     * Обновляет данные офиса
     * @param officeUpdateView отображение для обновления данных об офисе {@link OfficeUpdateView}
     * @return возвращается результат выполнения, пример: result = "success"
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public ResultView update(@Valid @RequestBody OfficeUpdateView officeUpdateView) {
        officeService.update(officeUpdateView);
        return new ResultView();
    }
}

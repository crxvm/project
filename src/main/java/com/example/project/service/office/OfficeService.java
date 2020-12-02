package com.example.project.service.office;

import com.example.project.view.office.*;

import java.util.List;

public interface OfficeService {
    OfficeView getById(Long id);
    void update(OfficeUpdateView officeUpdateView);
    void save(OfficeSaveView officeSaveView);
    List<OfficeListOutView> list(OfficeListInView officeListInView);
}

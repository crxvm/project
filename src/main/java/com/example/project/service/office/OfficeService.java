package com.example.project.service.office;

import com.example.project.view.OfficeFullView;
import com.example.project.view.OfficeListView;

import javax.swing.text.StyledEditorKit;

public interface OfficeService {
    OfficeFullView getById(Long id);
    void update(OfficeFullView officeFullView);
    void save(OfficeFullView officeFullView);
    OfficeListView list(Integer orgId, String name, String phone, Boolean isActive);
}

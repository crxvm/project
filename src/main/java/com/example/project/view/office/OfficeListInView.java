package com.example.project.view.office;

import javax.validation.constraints.NotNull;

public class OfficeListInView {
    @NotNull
    public Integer orgId;
    public String name;
    public String phone;
    public Boolean isActive;
}

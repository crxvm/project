package com.example.project.view.office;

import javax.validation.constraints.NotNull;

public class OfficeUpdateView {
    @NotNull
    public Long id;
    public String name;
    public String address;
    public String phone;
    public Boolean isActive;
}

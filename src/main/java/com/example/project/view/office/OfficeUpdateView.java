package com.example.project.view.office;

import javax.validation.constraints.NotNull;

public class OfficeUpdateView {
    @NotNull
    public Long id;
    @NotNull
    public String name;
    @NotNull
    public String address;
    public String phone;
    public Boolean isActive;
}

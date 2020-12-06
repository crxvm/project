package com.example.project.view.office;

import javax.validation.constraints.NotNull;

public class OfficeSaveView {
    @NotNull
    public Long orgId;
    public String name;
    public String address;
    public String phone;
    public Boolean isActive;
}

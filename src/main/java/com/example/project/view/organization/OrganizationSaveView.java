package com.example.project.view.organization;

import javax.validation.constraints.NotNull;

public class OrganizationSaveView {
    @NotNull
    public String name;
    @NotNull
    public String fullName;
    @NotNull
    public String inn;
    @NotNull
    public String kpp;
    @NotNull
    public String address;

    public String phone;
    public Boolean isActive;
}

package com.example.project.view.organization;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Организация")
public class OrganizationView {
    @NotNull
    public Long id;
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

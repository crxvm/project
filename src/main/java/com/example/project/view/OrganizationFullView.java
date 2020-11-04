package com.example.project.view;

import io.swagger.annotations.ApiModel;


@ApiModel(description = "Организация")
public class OrganizationFullView {

    public String id;
    public String name;
    public String fullName;
    public String inn;
    public String kpp;
    public String address;
    public String phone;
    public Boolean is_Active;

}

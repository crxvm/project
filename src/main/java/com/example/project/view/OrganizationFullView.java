package com.example.project.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Организация")
public class OrganizationFullView {

    public String id;
    public String name;
    public String fullName;
    public String kpp;
    public String address;
    public String phone;
    public String is_Active;

}

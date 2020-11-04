package com.example.project.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Документ")
public class CountryView {

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    @NotNull
    public String id;

    @ApiModelProperty(value = "Код")
    public String citizenshipCode;
    @ApiModelProperty(value = "Название страны")
    public String citizenshipName;
}

package com.example.project.view;

import javax.validation.constraints.NotNull;

public class CountryView {
    @NotNull
    public String id;
    public String citizenshipCode;
    public String citizenshipName;
}

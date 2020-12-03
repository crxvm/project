package com.example.project.view.organization;

import javax.validation.constraints.NotNull;

public class OrganizationListInView {
    @NotNull
    public String name;
    public String inn;
    public Boolean isActive;
}

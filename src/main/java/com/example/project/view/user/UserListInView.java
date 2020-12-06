package com.example.project.view.user;

import javax.validation.constraints.NotNull;

public class UserListInView {
    @NotNull
    public Integer officeId;
    public String firstName;
    public String secondName;
    public String middleName;
    public String position;
    public String docCode;
    public String citizenshipCode;
}

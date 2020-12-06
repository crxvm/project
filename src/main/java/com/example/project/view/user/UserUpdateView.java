package com.example.project.view.user;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserUpdateView {
    @NotNull
    public Long id;
    public Integer officeId;
    @NotNull
    public String firstName;
    public String secondName;
    public String middleName;
    @NotNull
    public String position;
    public String phone;
    public String docName;
    public String docNumber;
    public Date docDate;
    public String citizenshipCode;
    public Boolean isIdentified;

}

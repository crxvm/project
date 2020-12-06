package com.example.project.view.user;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserSaveView {
    @NotNull
    public Integer officeId;
    @NotNull
    public String firstName;
    public String secondName;
    public String middleName;
    @NotNull
    public String position;
    public String phone;
    public String docCode;
    public String docName;
    public String docNumber;
    public Date docDate;
    public String citizenshipCode;
    public Boolean isIdentified;

}

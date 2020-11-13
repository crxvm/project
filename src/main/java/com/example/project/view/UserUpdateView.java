package com.example.project.view;

import com.example.project.model.Country;
import com.example.project.model.Document;
import com.example.project.model.UserDocument;

import java.util.Date;

public class UserUpdateView {
    public Long id;
    public Long userId;
    public Integer officeId;
    public String firstName;
    public String secondName;
    public String middleName;
    public String position;
    public String phone;

    public String docCode;//
    public String docName;
    public String docNumber;
    public Date docDate;

    public String citizenshipCode;
    public Boolean isIdentified;

    public Country country;
    public UserDocument userDocument;
    public Document document;
}

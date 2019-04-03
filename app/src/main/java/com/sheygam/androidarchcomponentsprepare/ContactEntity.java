package com.sheygam.androidarchcomponentsprepare;

import java.util.Date;

//Todo Make Room entity
public class ContactEntity {
    private int id;
    private String name;
    private String phone;
    private Date birthDay;

    public ContactEntity(String name, String phone, Date birthDay) {
        this.name = name;
        this.phone = phone;
        this.birthDay = birthDay;
    }

    public ContactEntity(int id, String name, String phone, Date birthDay) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthDay = birthDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}

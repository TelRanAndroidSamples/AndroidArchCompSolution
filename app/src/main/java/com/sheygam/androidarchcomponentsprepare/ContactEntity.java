package com.sheygam.androidarchcomponentsprepare;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;
@Entity(tableName = "contacts")
public class ContactEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    @ColumnInfo(name = "birth_day")
    private Date birthDay;

    @Ignore
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

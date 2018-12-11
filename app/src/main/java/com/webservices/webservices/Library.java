package com.webservices.webservices;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Library implements Serializable {

    private String name;
    private String city;
    private String department;
    private String address;
    private String state;
    private String nature;
    private String type;
    private String phone;

    public Library(String name,
                   String city,
                   String department,
                   String address,
                   String state,
                   String nature,
                   String type,
                   String phone) {
        this.name = name;
        this.city = city;
        this.department = department;
        this.address = address;
        this.state = state;
        this.nature = nature;
        this.type = type;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

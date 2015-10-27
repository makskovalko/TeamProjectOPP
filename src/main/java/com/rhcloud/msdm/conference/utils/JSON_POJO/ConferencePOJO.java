package com.rhcloud.msdm.conference.utils.JSON_POJO;

import java.sql.Date;

/**
 * Created by Ghost on 24.10.2015.
 */
public class ConferencePOJO {
    private String name, country, city, address;
    private Date date;


    public ConferencePOJO() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


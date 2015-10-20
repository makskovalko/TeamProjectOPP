package com.rhcloud.msdm.conference.utils.JSON_POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ghost on 12.10.2015.
 */
public class FacebookProfile {


    @JsonProperty("first_name")
    private String firstname;

    private  FacebookPicture picture;
    private String id;
    @JsonProperty("last_name")
    private String lastname;

    @JsonProperty("birthday")
    private String birthday;



    public FacebookProfile() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public FacebookPicture getPicture() {
        return picture;
    }

    public void setPicture(FacebookPicture picture) {
        this.picture = picture;
    }

    public String getBithday() {
        return birthday;
    }

    public void setBithday(String birthday) {
        this.birthday = birthday;
    }
}

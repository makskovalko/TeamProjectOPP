package com.rhcloud.msdm.conference.utils.JSON_POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ghost on 11.10.2015.
 */
public class VkProfile {

    @JsonProperty("id")
    private String id;

    @JsonProperty("last_name")
    private String lastname ;

    @JsonProperty("first_name")
    private String firstname;

    @JsonProperty("photo_200_orig")
    private String photo;


    @JsonProperty("bdate")
    private String bithDay;


    public VkProfile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBithDay() {
        return bithDay;
    }

    public void setBithDay(String bithDay) {
        this.bithDay = bithDay;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}

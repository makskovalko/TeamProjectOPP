package com.rhcloud.msdm.conference.utils.JSON_POJO;

/**
 * Created by Ghost on 14.10.2015.
 */
public class UserRegInfo {

    private String firstname;

    private String lastname;

    private String photo;

    private String bithdate;


    public UserRegInfo() {
    }

    public String getBithdate() {
        return bithdate;
    }

    public void setBithdate(String bithdate) {
        this.bithdate = bithdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

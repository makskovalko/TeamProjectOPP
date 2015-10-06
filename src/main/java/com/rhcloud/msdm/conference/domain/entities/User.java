package com.rhcloud.msdm.conference.domain.entities;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

//Not Entity
public class User {

    public User() {}

    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String profileImage;
    private String userType;
    private String confirmURL;

    private Date dateOfBirth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getConfirmURL() {
        return confirmURL;
    }

    public void setConfirmURL(String confirmUrl) {
        this.confirmURL = confirmUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String generateConfirmKey() {
        String generatedKey = "";
        StringBuilder symbols = new StringBuilder();

        for (char c = 'a'; c <= 'z'; c++) symbols.append(c);
        for (char c = '@'; c <= 'Z'; c++) symbols.append(c);
        for (char c = '1'; c <= '9'; c++) symbols.append(c);

        for (int i = 0; i < 10; i++) generatedKey += symbols.charAt((int)(Math.random() * symbols.length()));

        return generatedKey;
    }

    @Override
    public String toString() {
        return String.format("[\n\tusername: %s\n\tpassword: %s\n\tfirstName: %s\n\tlastName: %s\n\te-mail: %s\n\tphoneNumber: %s\n]",
                userName, password, firstName, lastName, email, phoneNumber);
    }
}
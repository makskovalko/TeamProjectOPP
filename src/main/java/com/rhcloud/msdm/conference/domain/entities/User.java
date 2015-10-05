package com.rhcloud.msdm.conference.domain.entities;

//Not Entity
public abstract class User {

    public User() {}

    private Integer id;
    private String firstName, lastName, userName, password, email, phoneNumber, profileImage;

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
        this.password = password;
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

    public String generateConfirmKey() {
        String generatedKey = "";
        StringBuilder symbols = new StringBuilder();

        for (char c = 'a'; c <= 'z'; c++) symbols.append(c);
        for (char c = '@'; c <= 'Z'; c++) symbols.append(c);
        for (char c = '1'; c <= '9'; c++) symbols.append(c);

        for (int i = 0; i < 10; i++) generatedKey += symbols.charAt((int)(Math.random() * symbols.length()));

        return generatedKey;
    }
}
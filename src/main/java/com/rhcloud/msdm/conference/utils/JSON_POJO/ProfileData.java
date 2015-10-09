package com.rhcloud.msdm.conference.utils.JSON_POJO;

import java.util.Date;

public class ProfileData {

    private String firstName;

    private String lastName;

    private String userName;

    private String phoneNumber;

    private String workPlace;

    private String jobPosition;

    private String additionalInfo;

    private String profileImage;

    private Date dateOfBirth;


    public class Builder{

        private String firstName;

        private String lastName;

        private String userName;

        private String phoneNumber;

        private String workPlace;

        private String jobPosition;

        private String additionalInfo;

        private String profileImage;

        private Date dateOfBirth;

        public Builder(String userName){
            this.userName = userName;
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder workPlace(String workPlace){
            this.workPlace = workPlace;
            return this;
        }

        public Builder jobPosition(String jobPosition){
            this.jobPosition = jobPosition;
            return this;
        }

        public Builder additionalInfo(String additionalInfo){
            this.additionalInfo = additionalInfo;
            return this;
        }

        public Builder profileImage(String profileImage){
            this.profileImage = profileImage;
            return this;
        }

        public Builder dateOfBirth(Date dateOfBirth){
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public ProfileData build(){
            return new ProfileData(this);
        }
    }

    private ProfileData(Builder builder) {
        this.userName = builder.userName;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.workPlace = builder.workPlace;
        this.jobPosition = builder.jobPosition;
        this.additionalInfo = builder.additionalInfo;
        this.profileImage = builder.profileImage;
        this.dateOfBirth = builder.dateOfBirth;
    }

    public ProfileData(){}


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

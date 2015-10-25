package com.rhcloud.msdm.conference.domain.entities;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "organizer")
public class Organizer extends User {

    public Organizer() {
        this.confirmationKey = generateConfirmKey();
        this.active = 0;
    }

    public Organizer(User user) {
        this.confirmationKey = generateConfirmKey();
        this.active = 0;
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.dateOfBirth = user.getDateOfBirth();
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "active", length = 1)
    private Integer active;

    @Column(name = "confirmation_key", length = 10)
    private String confirmationKey;

    @Transient
    private String userType;

    @Column(name = "date_of_birth")
    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany
    @JoinColumn(name = "conference_id")
    private List<Conference> conferences = new ArrayList<Conference>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getConfirmationKey() {
        return confirmationKey;
    }

    public void setConfirmationKey(String confirmationKey) {
        this.confirmationKey = confirmationKey;
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

    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }

    public String getConfirmURL() {
        return "http://localhost:8080/confirm_email/organizer/" + userName + "/" + confirmationKey;
    }

    @Override
    public String toString() {
        return String.format("[\n\tusername: %s\n\tpassword: %s\n\tfirstName: %s\n\tlastName: %s\n\te-mail: %s\n\tphoneNumber: %s\n]",
                userName, password, firstName, lastName, email, phoneNumber);
    }

}
package com.rhcloud.msdm.conference.domain.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizer")
public class Organizer extends User {

    public Organizer() {
        this.confirmationKey = generateConfirmKey();
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

    @OneToMany(cascade = { CascadeType.ALL, CascadeType.PERSIST })
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

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
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
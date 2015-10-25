package com.rhcloud.msdm.conference.domain.pojo;

import com.rhcloud.msdm.conference.domain.entities.Conference;

import java.util.Date;

public class ConferenceJSON {

    public ConferenceJSON() {}

    public ConferenceJSON(Conference conference) {
        this.name = conference.getName();
        this.country = conference.getCountry();
        this.city = conference.getCity();
        this.address = conference.getAddress();
        this.description = conference.getDescription();
        this.participantLimit = conference.getParticipantLimit();
        this.organizerId = conference.getOrganizer().getId();
        this.categoryId = conference.getCategory().getId();
        this.date = conference.getDate();
    }

    private String name, country, city, address, description;

    private Integer participantLimit, organizerId, categoryId;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(Integer participantLimit) {
        this.participantLimit = participantLimit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s", name, country, city, address, description, participantLimit, organizerId, categoryId, date);
    }
}
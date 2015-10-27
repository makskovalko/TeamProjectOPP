package com.rhcloud.msdm.conference.utils.JSON_POJO;

import com.rhcloud.msdm.conference.domain.entities.Conference;

import java.util.Date;

public class ConferenceJSON {

    public ConferenceJSON() {}

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public ConferenceJSON(Conference conference) {
        this.id = conference.getId();
        this.name = conference.getName();
        this.country = conference.getCountry();
        this.city = conference.getCity();
        this.address = conference.getAddress();
        this.description = conference.getDescription();
        this.participantLimit = conference.getParticipantLimit();
        this.organizerId = conference.getOrganizer().getId();
        this.categoryId = conference.getCategory().getId();
        this.date = conference.getDate();
        this.ticketPrice = conference.getTicketPrice();
        this.participantCount = conference.getParticipantCount();
        this.color =  conference.getCategory().getColor();
    }

    private String name, country, city, address, description, ticketPrice, color;

    private Integer participantLimit;

    public Integer getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    private Integer participantCount;
    private Integer organizerId;
    private Integer categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s", name, country, city, address, description, participantLimit, organizerId, categoryId, date);
    }
}
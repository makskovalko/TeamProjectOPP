package com.rhcloud.msdm.conference.domain.entities;


import com.rhcloud.msdm.conference.utils.JSON_POJO.ConferenceJSON;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "conference")
public class Conference {

    public Conference() {}

    public Conference(ConferenceJSON conferenceJSON) {
        this.name = conferenceJSON.getName();
        this.country = conferenceJSON.getCountry();
        this.city = conferenceJSON.getCity();
        this.address = conferenceJSON.getAddress();
        this.date = conferenceJSON.getDate();
        this.participantLimit = conferenceJSON.getParticipantLimit();
        this.description = conferenceJSON.getDescription();
        this.ticketPrice = conferenceJSON.getTicketPrice();
        this.participantCount = conferenceJSON.getParticipantCount();
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private Integer participantLimit, participantCount;
    private String name;
    private String country;
    private String city;
    private String address;

    @Column(name = "ticket_price")
    private String ticketPrice;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    private Date date;

    @OneToOne
    private Category category;

    @ManyToOne
    private Organizer organizer;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "CONFERENCE_PARTICIPANT", joinColumns = {@JoinColumn(name = "conference_id")},
            inverseJoinColumns = {@JoinColumn(name = "participant_id")})
    private List<Participant> participants;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.PERSIST })
    @JoinTable(name = "CONFERENCE_SPEAKER", joinColumns = @JoinColumn(name = "conference_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers = new ArrayList<Speaker>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    @Column(name = "participant_limit")
    public Integer getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(Integer participantLimit) {
        this.participantLimit = participantLimit;
    }

    @Column(name = "participant_count")
    public Integer getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(Integer participantCount) {
        this.participantCount = participantCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return String.format("%s\n, %s\n, %s\n, %s\n, %s\n, %s\n, %s\n, %s\n, %s", name, country, city, address, description, participantLimit, organizer.getId(), category.getId(), date);
    }
}
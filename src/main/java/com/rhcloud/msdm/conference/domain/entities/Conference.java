package com.rhcloud.msdm.conference.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "conference")
public class Conference {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private Integer participantLimit, participantCount;
    private String name, country, city, address;
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

    @OneToOne(cascade = { CascadeType.ALL, CascadeType.PERSIST })
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.PERSIST })
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

    public void setParticipantLimit(int participantLimit) {
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
}
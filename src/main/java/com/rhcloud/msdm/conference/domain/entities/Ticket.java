package com.rhcloud.msdm.conference.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    private int id, conference_id, participant_id;
    private String number, token;

    @OneToOne(cascade = { CascadeType.ALL, CascadeType.PERSIST })
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @OneToOne(cascade = { CascadeType.ALL, CascadeType.PERSIST })
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "conference_id")
    public int getConference_id() {
        return conference_id;
    }

    public void setConference_id(int conference_id) {
        this.conference_id = conference_id;
    }

    @Column(name = "participant_id")
    public int getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(int participant_id) {
        this.participant_id = participant_id;
    }

    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String tocken) {
        this.token = tocken;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
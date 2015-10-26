package com.rhcloud.msdm.conference.domain.entities;

import javax.persistence.*;

/**
 * Created by Ghost on 18.10.2015.
 */
@Entity
@Table(name="offer")
public class Offer {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name ="text")
    private String text;


    @ManyToOne(cascade = { CascadeType.ALL, CascadeType.PERSIST })
    @JoinColumn(name = "conference_id")
    private  Conference conference;


    @ManyToOne(cascade = { CascadeType.ALL, CascadeType.PERSIST })
    @JoinColumn(name = "speaker_id")
    private  Speaker  speaker;



    public Offer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {

        this.conference = conference;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

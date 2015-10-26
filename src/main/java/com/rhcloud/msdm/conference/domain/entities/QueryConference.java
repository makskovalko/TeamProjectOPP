package com.rhcloud.msdm.conference.domain.entities;

import javax.persistence.*;

/**
 * Created by Ghost on 23.10.2015.
 */
@Entity
@Table(name = "query_to_conference")
public class QueryConference {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;


    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private  Speaker speaker;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private  Conference conference;


    public QueryConference() {
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

    public Integer getIdt() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

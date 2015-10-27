package com.rhcloud.msdm.conference.domain.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(name = "messages")
public class Messages {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private Integer conferenceId, userId;
    private String topic, description;

    public Messages(){}

    public Messages(Integer conferenceId, Integer userId, String topic, String description){
        this.conferenceId = conferenceId;
        this.userId = userId;
        this.topic = topic;
        this.description = description;
    }

    @ManyToOne
    private Organizer organizer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
}

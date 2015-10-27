package com.rhcloud.msdm.conference.utils.JSON_POJO;

/**
 * Created by Ghost on 25.10.2015.
 */
public class OfferPOJO {

    private String text;

    private  ConferencePOJO conference;

    private  SpeakerPOJO speaker;



    public OfferPOJO() {
    }

    public ConferencePOJO getConference() {
        return conference;
    }

    public void setConference(ConferencePOJO conference) {
        this.conference = conference;
    }

    public SpeakerPOJO getSpeaker() {
        return speaker;
    }

    public void setSpeaker(SpeakerPOJO speaker) {
        this.speaker = speaker;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

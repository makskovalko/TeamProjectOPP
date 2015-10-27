package com.rhcloud.msdm.conference.service.Impl;

import com.rhcloud.msdm.conference.domain.entities.*;
import com.rhcloud.msdm.conference.repository.MessagesRepository;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ConferenceJSON;
import com.rhcloud.msdm.conference.repository.CategoryRepository;
import com.rhcloud.msdm.conference.repository.ConferenceRepository;
import com.rhcloud.msdm.conference.repository.OrganizerRepository;
import com.rhcloud.msdm.conference.service.Interfaces.OrganizerActions;
import com.rhcloud.msdm.conference.utils.JSON_POJO.MessageJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrganizerService implements OrganizerActions {

    @Autowired private OrganizerRepository organizerRepository;
    @Autowired private ConferenceRepository conferenceRepository;
    @Autowired private CategoryRepository categoryRepository;

    @Resource(name = "messagesRepository")
    private MessagesRepository messagesRepository;

    @Override
    public void updateData(Organizer organizer) {
        Organizer newOrganizer = organizerRepository.findOrganizerByEmailOrUserName("", organizer.getUserName());
        newOrganizer.setFirstName(organizer.getFirstName());
        newOrganizer.setLastName(organizer.getLastName());
        //newOrganizer.setEmail(organizer.getEmail());
        newOrganizer.setPhoneNumber(organizer.getPhoneNumber());
        newOrganizer.setDateOfBirth(organizer.getDateOfBirth());
        organizerRepository.saveAndFlush(newOrganizer);
    }

    @Override
    public Organizer getOrganizerByUserName(String userName) {
        return organizerRepository.findOrganizerByEmailOrUserName("", userName);
    }

    @Override
    public Conference createConference(ConferenceJSON conferenceJSON) {

        Conference conference = new Conference(conferenceJSON);
        Organizer organizer = organizerRepository.findOne(conferenceJSON.getOrganizerId());
        Category category = categoryRepository.findOne(conferenceJSON.getCategoryId());

        conference.setOrganizer(organizer);
        conference.setCategory(category);

        conferenceRepository.saveAndFlush(conference);

        return conference;
    }

    public List<Conference> findConferencesByName(String name) {
        return conferenceRepository.findAllConferencesByNameContaining(name);
    }

    @Override
    public Messages sendMessage(MessageJSON messageJSON){

       Messages message = new Messages(messageJSON.getConferenceId(), messageJSON.getUserId(), messageJSON.getTopic(), messageJSON.getDescription());
        message.setOrganizer(organizerRepository.findOne(messageJSON.getOrganizerId()));
        messagesRepository.saveAndFlush(message);
        return message;
    }

    @Override
    public Organizer getOrganizerById(Integer id) {
        return organizerRepository.findOne(id);
    }

}
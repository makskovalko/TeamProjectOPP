package com.rhcloud.msdm.conference.service.Impl;

import com.rhcloud.msdm.conference.domain.entities.Category;
import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.pojo.ConferenceJSON;
import com.rhcloud.msdm.conference.repository.CategoryRepository;
import com.rhcloud.msdm.conference.repository.ConferenceRepository;
import com.rhcloud.msdm.conference.repository.OrganizerRepository;
import com.rhcloud.msdm.conference.service.Interfaces.OrganizerActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class OrganizerService implements OrganizerActions {

    @Autowired private OrganizerRepository organizerRepository;
    @Autowired private ConferenceRepository conferenceRepository;
    @Autowired private CategoryRepository categoryRepository;

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

        JOptionPane.showMessageDialog(null, organizer.toString());
        JOptionPane.showMessageDialog(null, category.getName());

        conference.setOrganizer(organizer);
        conference.setCategory(category);

        conferenceRepository.saveAndFlush(conference);

        return conference;
    }
}
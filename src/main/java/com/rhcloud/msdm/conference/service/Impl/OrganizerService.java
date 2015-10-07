package com.rhcloud.msdm.conference.service.Impl;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.repository.OrganizerRepository;
import com.rhcloud.msdm.conference.service.Interfaces.OrganizerActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService implements OrganizerActions {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Override
    public void updateData(Organizer organizer) {
        Organizer newOrganizer = organizerRepository.findOrganizerByEmailOrUserName("", organizer.getUserName());
        newOrganizer.setFirstName(organizer.getFirstName());
        newOrganizer.setLastName(organizer.getLastName());
        newOrganizer.setEmail(organizer.getEmail());
        newOrganizer.setPhoneNumber(organizer.getPhoneNumber());
        newOrganizer.setDateOfBirth(organizer.getDateOfBirth());
        organizerRepository.saveAndFlush(newOrganizer);
    }

    @Override
    public Organizer getOrganizerByUserName(String userName) {
        return organizerRepository.findOrganizerByEmailOrUserName("", userName);
    }
}
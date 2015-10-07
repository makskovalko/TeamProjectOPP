package com.rhcloud.msdm.conference.service.Interfaces;

import com.rhcloud.msdm.conference.domain.entities.Organizer;

public interface OrganizerActions {
    public void updateData(Organizer organizer);
    public Organizer getOrganizerByUserName(String userName);
}
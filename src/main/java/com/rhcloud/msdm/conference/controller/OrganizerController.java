package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.service.Impl.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @RequestMapping(value = "/updateOrganizerData", method = RequestMethod.POST, consumes = "application/json")
    public Organizer updateOrganizerData(@RequestBody Organizer organizer) {
        organizerService.updateData(organizer);
        return organizerService.getOrganizerByUserName(organizer.getUserName());
    }
}
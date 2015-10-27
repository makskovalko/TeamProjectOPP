package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.service.Interfaces.ConferenceTicketActions;
import com.rhcloud.msdm.conference.utils.FileUploader;
import com.rhcloud.msdm.conference.utils.GoogleDriveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;

@Controller
public class ConferenceTicketController {

    @Resource(name = "conferenceTicketService")
    private ConferenceTicketActions conferenceTicketActions;

    @Resource(name = "fileUploaderService")
    private FileUploader fileUploaderService;

    @Resource(name = "googleDriveService")
    private GoogleDriveService googleDriveService;


    @RequestMapping(value = "/conference/{id}", method = RequestMethod.GET)
    public String conference(@PathVariable("id") Integer id, Model model, HttpSession session) throws IOException, GeneralSecurityException {

        Conference conference = conferenceTicketActions.getConferenceById(id);
        model.addAttribute("conferenceData", conference);
        model.addAttribute("dayOfWeek", this.getDayOfWeek(conference.getDate()));


        String fileName = ((User) session.getAttribute("user")).getUserName();
        if ( fileUploaderService.fileExists(fileName) != null || googleDriveService.download(fileName)) {
            model.addAttribute("profileImg", "../../resources/ProfileImagesBufferDir/" + fileUploaderService.fileExists(fileName).getName());
        } else {
            model.addAttribute("profileImg", "../../resources/img/default.gif");
        }

        return "profiles/conferenceTicket";
    }

    /*@RequestMapping(value = "/conference/edit/{id}", method = RequestMethod.GET)
    public String editConference(@PathVariable("id") Integer id, HttpSession session) {
        Organizer organizer = (Organizer) session.getAttribute("user");

        return ""
    }*/


    public String getDayOfWeek(Date date){
        Calendar c = Calendar.getInstance();
        c.set(date.getYear(), date.getMonth(), date.getDay());

        switch (c.get(Calendar.DAY_OF_WEEK)){
            case 1: return "Воскресенье";
            case 2: return "Понедельник";
            case 3: return "Вторник";
            case 4: return "Среда";
            case 5: return "Четверг";
            case 6: return "Пятница";
            case 7: return "Суббота";
            default: return null;
        }
    }

}

package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.Category;
import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.repository.CategoryRepository;
import com.rhcloud.msdm.conference.repository.ConferenceRepository;
import com.rhcloud.msdm.conference.service.Interfaces.ConferenceTicketActions;
import com.rhcloud.msdm.conference.utils.FileUploader;
import com.rhcloud.msdm.conference.utils.GoogleDriveService;
import com.rhcloud.msdm.conference.utils.UploadStatus;
import com.rhcloud.msdm.conference.utils.exeptions.BufferDirIsNotDirectoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Resource(name = "conferenceTicketService")
    private ConferenceTicketActions conferenceTicketActions;

    @Resource(name = "fileUploaderService")
    private FileUploader fileUploaderService;

    @Resource(name = "googleDriveService")
    private GoogleDriveService googleDriveService;

    @Resource(name = "categoryRepository")
    private CategoryRepository categoryRepository;


    @RequestMapping(value = "/profile/participant", method = RequestMethod.GET)
    public String participantView(Model model, HttpSession session) throws GeneralSecurityException, IOException {
        String fileName = ((User) session.getAttribute("user")).getUserName();
        if ( fileUploaderService.fileExists(fileName) != null || googleDriveService.download(fileName)) {
            model.addAttribute("profileImg", "../resources/ProfileImagesBufferDir/" + fileUploaderService.fileExists(fileName).getName());
        } else {
            model.addAttribute("profileImg", "../resources/img/default.gif");
        }

        model.addAttribute("lastConferences", conferenceTicketActions.getLastConferences(10));

        return "profiles/participant";
    }

    @RequestMapping(value = "/profile/organizer", method = RequestMethod.GET)
    public String organizerView(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("conferenceCategories", categoryRepository.findAll());
        modelMap.addAttribute("allConferences", conferenceRepository.findAll());
        modelMap.addAttribute("myCreatedConferences",
                conferenceRepository.findAllConferencesByOrganizerId(((Organizer) session.getAttribute("user")).getId()));
        return "profiles/organizer";
    }

    @RequestMapping(value = "/profile/speaker", method = RequestMethod.GET)
    public String speakerView() {
        return "profiles/speaker";
    }

    @RequestMapping(value = "/upload/profile/img", method = RequestMethod.POST)
    @ResponseBody
    public UploadStatus upload(@RequestParam("file") MultipartFile file, HttpSession session) throws GeneralSecurityException, IOException {

        return fileUploaderService.uploadToLocalMachineAndGoogleDrive(file, ((User) session.getAttribute("user")).getUserName());
    }
}
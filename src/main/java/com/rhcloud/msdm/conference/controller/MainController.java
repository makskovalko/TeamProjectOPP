package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.utils.FileUploader;
import com.rhcloud.msdm.conference.utils.UploadStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Resource(name = "uploader")
    private FileUploader fileUploader;

    @RequestMapping(value = "/profile/participant", method = RequestMethod.GET)
    public String participantView(Model model, HttpSession session) {
        //Да-да это ужасно потом перепишу, когда определимся c путями :)
        model.addAttribute("profileImg",
                "../resources/ProfileImagesBufferDir/" + ((User)session.getAttribute("user")).getUserName() + ".jpg");

        return "profiles/participant";
    }


    @RequestMapping(value = "/profile/organizer", method = RequestMethod.GET)
    public String organizerView() {
        return "profiles/organizer";
    }

    @RequestMapping(value = "/profile/speaker", method = RequestMethod.GET)
    public String speakerView() {
        return "profiles/speaker";
    }

    @RequestMapping(value = "/upload/profile/img", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpSession session) {

        if (fileUploader.upload(file, ((User) session.getAttribute("user")).getUserName()) == UploadStatus.SUCCESS) {
            return "Ok";
        }

        return "Error";
    }
}
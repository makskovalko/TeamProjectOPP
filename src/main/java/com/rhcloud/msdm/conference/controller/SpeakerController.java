package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.entities.*;
import com.rhcloud.msdm.conference.repository.ConferenceRepository;
import com.rhcloud.msdm.conference.service.Impl.OfferService;
import com.rhcloud.msdm.conference.service.Interfaces.QueryConferenceAction;
import com.rhcloud.msdm.conference.service.Interfaces.SpeakerAction;
import com.rhcloud.msdm.conference.utils.FileUploader;
import com.rhcloud.msdm.conference.utils.GoogleDriveService;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ConferencePOJO;
import com.rhcloud.msdm.conference.utils.JSON_POJO.OfferPOJO;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ProfileData;
import com.rhcloud.msdm.conference.utils.JSON_POJO.QueryConferencePOJO;
import com.rhcloud.msdm.conference.utils.converter.ConferenceConvector;
import com.rhcloud.msdm.conference.utils.converter.OfferConvert;
import com.rhcloud.msdm.conference.utils.converter.QueryConferenceConverter;
import com.rhcloud.msdm.conference.utils.converter.SpeakerConverter;
import com.rhcloud.msdm.conference.utils.exeptions.BufferDirIsNotDirectoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ghost on 17.10.2015.
 */
@Controller
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    private OfferService offerAction;

    @Autowired
    private SpeakerAction speakerAction;

    @Autowired
    private SpeakerConverter speakerConverter;

    @Autowired
    private QueryConferenceAction queryConferenceAction;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private ConferenceConvector conferenceConvector;


    @Autowired
    private OfferConvert offerConvert;


    @Autowired
    private QueryConferenceConverter queryConferenceConverter;

    @Resource(name = "fileUploaderService")
    private FileUploader fileUploader;




    @RequestMapping(value = "/profile")
    @ResponseStatus(HttpStatus.OK)
    private String getProfile(HttpSession session , ModelMap model) {
        String view = "index";
        try {
            Speaker speaker = (Speaker) session.getAttribute("speaker");
            if (speaker != null) {
                model.addAttribute("speaker" , speaker);
                view = "profiles/speaker";
            } else
            {
                view = "redirect:/";
            }

        }
        catch (Exception ex) {
            view = "error";
            model.addAttribute("errorMessage", ex);
            ex.printStackTrace();
        }
        return view;
    }


    @RequestMapping(value = "/profile/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView profile(@PathVariable("username") String username){
        ModelAndView model = new ModelAndView();
        try{
            Speaker speaker =  speakerAction.getByUserName(username);
            if(speaker != null) {
                model.addObject("speaker", speaker);
                model.setViewName("profiles/speaker");
            }
            else {
                model.addObject("errorMessage", "Спикер не найден(");
                model.setViewName("error");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
            model.addObject("errorMessage", "Ошыбка(");
            model.setViewName("error");
        }
        return model;
    }


    @RequestMapping(value = "/conferences", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ConferencePOJO> getConference(HttpSession session) {
        Speaker speaker = (Speaker) session.getAttribute("speaker");
        if(speaker != null) {
            List<Conference> conferences = speakerAction.getConference(speaker);
            List<ConferencePOJO> res = new ArrayList<ConferencePOJO>();
            for (Conference conference : conferences) {
                res.add(conferenceConvector.convert(conference));
            }
            try {
                return res;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        else{
            return  null;
        }
    }


    @RequestMapping(value = "/update", method = {RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody ProfileData profileData, HttpSession session) {
        Speaker speaker = (Speaker) session.getAttribute("speaker");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html ; charset=UTF-8");
        ResponseEntity<String> response;
        try {
            Speaker updateSpeaker = speakerConverter.convert(profileData);
            updateSpeaker.setId(speaker.getId());
            speakerAction.update(updateSpeaker);
            session.setAttribute("speaker", updateSpeaker);
            response =
                    new ResponseEntity<String>("{\"message\" : \"Данные обновленны\"}", headers, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            response =
                    new ResponseEntity<String>("{\"message\" : \"Ошибка:данные не обновленны \"}", headers, HttpStatus.NOT_MODIFIED);
        }
        return response;
    }


    @RequestMapping(value = "/send/query/{conferenceId}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<String> sendOffer(@PathVariable("conferenceId") Integer conferenceId,
                                            @RequestParam("text") String text,
                                            HttpSession session) {
        Speaker speaker = (Speaker) session.getAttribute("speaker");
        ResponseEntity<String> response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html; charset=UTF-8");
        try {
            Conference conference = conferenceRepository.findOne(conferenceId);
            Speaker speaker1 = speakerAction.getById(speaker.getId());
            QueryConference query = new QueryConference();
            query.setConference(conference);
            query.setSpeaker(speaker1);
            query.setText(text);
            queryConferenceAction.save(query);
            response =
                    new ResponseEntity<String>("{\"message\" : \"Запрос был отослан\"}", headers, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            response =
                    new ResponseEntity<String>("{\"message\" : \"Ошыбка: запрос не был отослан\"}", headers, HttpStatus.BAD_REQUEST);
        }
        return response;
    }



    @RequestMapping(value = "/get/offers", method = {RequestMethod.GET})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<OfferPOJO> getOffer(HttpSession session) {
        Speaker speaker = (Speaker) session.getAttribute("speaker");
        List<OfferPOJO> res = new ArrayList<>();
        try {
            List<Offer> offers = speakerAction.getOffers(speaker);
            for(Offer offer : offers){
                OfferPOJO pojo = offerConvert.convert(offer);
                res.add(pojo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return  res;
    }
    @RequestMapping(value = "/get/queries" , method = RequestMethod.GET)
    @ResponseBody
    public List<QueryConferencePOJO> getQueryConference(HttpSession session){
        Speaker speaker = (Speaker) session.getAttribute("speaker");
        List<QueryConferencePOJO> res = new ArrayList<>();
        try{
            List<QueryConference> queryConferences = speakerAction.getQueries(speaker);
            for(QueryConference queryConference: queryConferences){
                res.add(queryConferenceConverter.convert(queryConference));
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return  res;
    }


    @RequestMapping(value = "/agree/offer/{offerId}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<String> agreeOffer(@PathVariable("offerId") Integer offerId,
                                             HttpSession session) {
        Speaker speaker = (Speaker) session.getAttribute("speaker");
        ResponseEntity<String> response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        try {
            Offer offer = offerAction.getById(offerId);
            if (offer.getSpeaker().getId() == speaker.getId()) {
                speakerAction.agree(offer);
                response = new ResponseEntity<String>("{\"message\" : \"Предложение принято\"}" ,headers ,HttpStatus.OK);
            }
            else {

                response =
                        new ResponseEntity<String>("{\"message\" : \"Даное предложение поступило не вам\"}" , headers, HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response =
                    new ResponseEntity<String>("{\"message\" : \"Ошибка\"}", headers, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return response;
    }

    @RequestMapping(value = "/update/image")
    @ResponseBody
    public ResponseEntity<String> updateProfileImage(@RequestParam("photo") MultipartFile photo, Speaker speaker) {
        ResponseEntity<String> response = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", "application/json; charset=UTF-8");
            response =
                    new ResponseEntity<String>("{ \"message\": \"Фото обновлено\", }", headers, HttpStatus.OK);
            fileUploader.uploadToLocalMachineAndGoogleDrive(photo, (speaker).getUserName());
            speakerAction.updatePhoto(speaker);
        } catch (IOException | GeneralSecurityException ex) {
            ex.printStackTrace();
            response =
                    new ResponseEntity<String>("{ \"message\": \"Фото не обновлено\" }", headers, HttpStatus.NOT_MODIFIED);
        }
        return response;
    }

    public ConferenceConvector getConferenceConvector() {
        return conferenceConvector;
    }

    public void setConferenceConvector(ConferenceConvector conferenceConvector) {
        this.conferenceConvector = conferenceConvector;
    }

    public ConferenceRepository getConferenceRepository() {
        return conferenceRepository;
    }

    public void setConferenceRepository(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public OfferService getOfferAction() {
        return offerAction;
    }

    public void setOfferAction(OfferService offerAction) {
        this.offerAction = offerAction;
    }

    public OfferConvert getOfferConvert() {
        return offerConvert;
    }

    public void setOfferConvert(OfferConvert offerConvert) {
        this.offerConvert = offerConvert;
    }

    public QueryConferenceAction getQueryConferenceAction() {
        return queryConferenceAction;
    }

    public void setQueryConferenceAction(QueryConferenceAction queryConferenceAction) {
        this.queryConferenceAction = queryConferenceAction;
    }

    public QueryConferenceConverter getQueryConferenceConverter() {
        return queryConferenceConverter;
    }

    public void setQueryConferenceConverter(QueryConferenceConverter queryConferenceConverter) {
        this.queryConferenceConverter = queryConferenceConverter;
    }

    public SpeakerAction getSpeakerAction() {
        return speakerAction;
    }

    public void setSpeakerAction(SpeakerAction speakerAction) {
        this.speakerAction = speakerAction;
    }

    public SpeakerConverter getSpeakerConverter() {
        return speakerConverter;
    }

    public void setSpeakerConverter(SpeakerConverter speakerConverter) {
        this.speakerConverter = speakerConverter;
    }
}

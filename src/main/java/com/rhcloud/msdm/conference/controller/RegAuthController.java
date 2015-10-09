package com.rhcloud.msdm.conference.controller;

import com.rhcloud.msdm.conference.domain.UserFactory;
import com.rhcloud.msdm.conference.domain.entities.User;
import com.rhcloud.msdm.conference.service.Impl.AuthorizationService;
import com.rhcloud.msdm.conference.service.Impl.RegistrationService;
import com.rhcloud.msdm.conference.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegAuthController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private AuthorizationService authorizationService;

    @Resource(name = "mailSenderService")
    private MailSender mailSender;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model) {
        return "index";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> signUp(@RequestBody User user) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/plain;charset=UTF-8");

        User rightUser = user != null ? UserFactory.getUserByType(user) : null;

        if (rightUser != null && registrationService.checkData(rightUser)) {
            mailSender.sendMail(rightUser.getEmail(), "Conference Registration", rightUser.getConfirmURL());
            return new ResponseEntity<String>(
                    "Подтвердите свою регистрацию, перейдя по ссылке в письме, высланном Вам на e-mail", httpHeaders, HttpStatus.OK
            );
        }
        else return new ResponseEntity<String>(
                "Ошибка регистрации! Пользователь с таким логином или e-mail адресом уже зарегистрирован!", httpHeaders, HttpStatus.OK
        );
    }


    @RequestMapping(value = "/confirm_email/{user}/{userName}/{confirmKey}")
    @ResponseBody
    public ResponseEntity<String> confirmEmail(@PathVariable("user") String user,
                                               @PathVariable("userName") String userName,
                                               @PathVariable(value = "confirmKey") String confirmKey) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/plain;charset=UTF-8");

        if (registrationService.confirmRegistration(user, userName, confirmKey))
            return new ResponseEntity<String>("Регистрация прошла успешно!", httpHeaders, HttpStatus.OK);
        else return new ResponseEntity<String>("Ошибка регистрации!", httpHeaders, HttpStatus.OK);
    }


    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> signIn(@RequestBody User user, HttpServletRequest request) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "text/plain;charset=UTF-8");

        User rightUser = user != null ? UserFactory.getUserByType(user) : null;

        if (rightUser != null && authorizationService.authorizeUser(rightUser) != null) {
            User authUser = authorizationService.authorizeUser(rightUser);
            authUser.setUserType(user.getUserType());
            request.getSession().setAttribute("user", authUser);
            return new ResponseEntity<String>(user.getUserType(), httpHeaders, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Ошибка авторизации!", httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null)
            request.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
package com.rhcloud.msdm.conference.security;

import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ParticipantInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && user instanceof Participant) return true;

        response.sendRedirect("/");
        return false;
    }
}

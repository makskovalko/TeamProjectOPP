package com.rhcloud.msdm.conference.security;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.domain.entities.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MainInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user instanceof Participant) {
                response.sendRedirect("/profiles/participant");
                return true;
            } else if (user instanceof Speaker) {
                response.sendRedirect("/profiles/speaker");
                return true;
            } else if (user instanceof Organizer) {
                response.sendRedirect("/profiles/organizer");
                return true;
            }
        }

        return true;
    }
}
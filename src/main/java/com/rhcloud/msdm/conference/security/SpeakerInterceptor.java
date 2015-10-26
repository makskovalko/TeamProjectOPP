package com.rhcloud.msdm.conference.security;

import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.domain.entities.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SpeakerInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null && user instanceof Speaker) return true;

            response.sendRedirect("/");
            return false;
        }
}

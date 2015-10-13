package com.rhcloud.msdm.conference.controller.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhcloud.msdm.conference.utils.JSON_POJO.FacebookProfile;
import com.rhcloud.msdm.conference.utils.URLRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ghost on 07.10.2015.
 */
@Controller
public class FacebookController {

    private final  static String appId= "1670358689874646";

    private final  static String appSecret= "1c8ef564175dd2e8de500935a8718ef4";

    private  final  static String callbackUrl = "http://localhost:8081/facebook/callback";


    @Autowired
    private URLRequestUtil urlRequestUtil;



    @RequestMapping(value = "/facebook/signUp")
    public String signUp(HttpServletRequest request, ModelMap model) {
        String accessToken = (String) request.getSession().getAttribute("facebookToken");
        if(accessToken != null) {
            String data =
                    urlRequestUtil.sendRequest(
                            "https://graph.facebook.com/me?access_token=" + accessToken +
                                    "&fields=last_name,birthday,first_name,picture.type(large)"
                    );

            try {

                ObjectMapper mapper = new ObjectMapper();
                FacebookProfile profile = mapper.readValue(data, FacebookProfile.class);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return "res";
        }
        else {
            return "redirect:/facebook/login";
        }
    }


    @RequestMapping(value = "/facebook/callback")
    public String callback(ModelMap model, @RequestParam("code") String code , HttpServletRequest request)
    {
        String response = urlRequestUtil.sendRequest(
                "https://graph.facebook.com/oauth/access_token?client_id="
                +appId+"&redirect_uri="+callbackUrl
                +"&client_secret="+appSecret+"&code="+code);

        String token = getTokken(response);
        request.getSession().setAttribute("facebookToken" , token);
        if(true){
            return "redirect:/facebook/signUp";
        }
        return  "redirect:/facebook/signUp";

    }


    @RequestMapping( value = "/facebook/login")
    public String requestToLogin(HttpServletResponse response) throws IOException {
       return  "redirect:https://graph.facebook.com/oauth/authorize?&client_id="+appId
          +"&redirect_uri="+callbackUrl;
    }
    public String getTokken(String st){
        String t  = st.split("=")[1];
        String token = t.split("&")[0];
        return token;
    }
}

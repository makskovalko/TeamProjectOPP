package com.rhcloud.msdm.conference.controller.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhcloud.msdm.conference.utils.JSON_POJO.UserRegInfo;
import com.rhcloud.msdm.conference.utils.JSON_POJO.VkProfile;
import com.rhcloud.msdm.conference.utils.JSON_POJO.VkProfileContainer;
import com.rhcloud.msdm.conference.utils.URLRequestUtil;
import com.rhcloud.msdm.conference.utils.converter.VkConverter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class VkController {

    private final String callbackUrl = "http://localhost:8080/vk/callback";

    private final static String appId = "5102237";

    private final static String appSecret = "F9zh16nh7DvG1BOfzool";


    @Autowired
    private URLRequestUtil urlRequestUtil;

    @Autowired
    private VkConverter vkConverter;

    @RequestMapping(value = "/vk/callback")
    public String callback(@RequestParam("code") String code , HttpServletRequest request)  {
        String data = new URLRequestUtil().sendRequest("https://oauth.vk.com/access_token?client_id="
                + appId + "&client_secret=" + appSecret + "&redirect_uri=" + callbackUrl + "&code=" + code);
        String token = null;
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(data);
            token = (String) json.get("access_token");
         } catch (ParseException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("VkToken" , token);
        return "redirect:/vk/signUp";

    }

    @RequestMapping(value = "/vk/login")
    public void login(HttpServletResponse response) throws IOException {
       response.sendRedirect("https://oauth.vk.com/authorize?client_id="
               +appId+"&display=page&redirect_uri="+callbackUrl+"&scope=friends&response_type=code&v=5.37");
    }

    @ResponseBody
    public UserRegInfo signUp(HttpServletRequest request){
        String token = (String)request.getSession().getAttribute("VkToken");
        if(token != null) {
            String userRequest = "https://api.vk.com/method/users.get?v=5.37&access_token=" + token + "&fields=bdate,photo_200_orig";
            String data = urlRequestUtil.sendRequest(userRequest);
            VkProfileContainer profiles = null;
            VkProfile profile = null;
            try {
                profiles = new ObjectMapper().readValue(data, VkProfileContainer.class);
                profile = profiles.getVkProfileList().get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  vkConverter.convert(profile);
        }
        return null;
    }


    public URLRequestUtil getUrlRequestUtil() {
        return urlRequestUtil;
    }
    public void setUrlRequestUtil(URLRequestUtil urlRequestUtil){
        this.urlRequestUtil= urlRequestUtil;
    }

    public VkConverter getVkConverter() {
        return vkConverter;
    }

    public void setVkConverter(VkConverter vkConverter) {
        this.vkConverter = vkConverter;
    }
}

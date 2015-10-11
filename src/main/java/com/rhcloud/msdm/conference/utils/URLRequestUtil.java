package com.rhcloud.msdm.conference.utils;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ghost on 11.10.2015.
 */
@Service
public class URLRequestUtil {


    public String sendRequest(String request){
        URL tokenUrl = null;
        HttpURLConnection connection = null;
        try {
            tokenUrl = new URL(request);
            connection = (HttpURLConnection) tokenUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream() ,"UTF-8"));
            {
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return  response.toString();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

package com.cheksiteup.network;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteUpController {

    private String SITE_IS_UP = "Site is up and running !";
    private String SITE_IS_DOWN = "Site is up down !";
    private String INCORRECT_URL = "URL is incorrect";
    private String BLANK_URL = "URL is blank";

    @GetMapping("/check")
    public String checkSiteUp(@RequestParam String url) {
        String returnMessage = "";
        if (url.isEmpty()) {
            returnMessage = BLANK_URL;
        }
        try {
            URL objUrl = new URL(url);
            try {
                HttpURLConnection conn = (HttpURLConnection) objUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCodeCat = conn.getResponseCode()/100;
                if(responseCodeCat != 2 || responseCodeCat != 3){
                    returnMessage = SITE_IS_DOWN;
                }
                returnMessage = SITE_IS_UP;
            } catch (IOException e) {
                returnMessage = SITE_IS_DOWN;
            }
        } catch (MalformedURLException e) {
            returnMessage = INCORRECT_URL;
        }

        return returnMessage;
    }
    
}

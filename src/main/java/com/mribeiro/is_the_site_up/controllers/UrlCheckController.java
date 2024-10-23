package com.mribeiro.is_the_site_up.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "Yay! Site is up!";
    private final String SITE_IS_DOWN = "I am sorry, this site is down!";
    private final String INCORRECT_URL = "Please, review: URL is incorrect!";



    @GetMapping("check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMsg = "";
        try {
            URL urlObject = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode()/100;
            //check the status to make sure the connection is done but what code
            if (responseCodeCategory != 2 || responseCodeCategory != 3){
                returnMsg = SITE_IS_UP;
            }
            else{
                returnMsg = SITE_IS_DOWN;
            }
        } catch (MalformedURLException e){
            returnMsg = INCORRECT_URL;
        } 
        catch (IOException e) {
            returnMsg = SITE_IS_DOWN;
        }
        return returnMsg;
    }
    
    



}

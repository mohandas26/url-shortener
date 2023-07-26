package com.urlshorten.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.urlshorten.entity.URL;

@Service
public class BaseConversions {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = allowedString.toCharArray();
    private int base = allowedCharacters.length;
    Logger log = Logger.getLogger(BaseConversions.class.getName());
    
    public String encode(URL url){
        var encodedString = new StringBuilder();
        int input = url.getId();
        log.info("Starting ENCODE");
        if(input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }

        return encodedString.reverse().toString();
    }

    public int decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;

        var decoded = 0;

        //counter is used to avoid reversing input string
        var counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += allowedString.indexOf(characters[i]) * Math.pow(base, length - counter);
          
            counter++;
        }
        return decoded;
    }
}

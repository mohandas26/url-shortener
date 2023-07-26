package com.urlshorten.controller;

import com.urlshorten.entity.URL;
import com.urlshorten.service.URLShortenService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

@RestController
public class MainController {

    Logger log = Logger.getLogger(MainController.class.getName());
    @Autowired
    URLShortenService service;

    @PostMapping("/createShortURL/")
    public String mapURL(@RequestBody String userURL){
        log.info("Mapping Url Value on the database started");
        return service.createShortURL(userURL);
    }

    @GetMapping("/mapToOriginalURL/{key}")
    public ResponseEntity<Void> getOriginalUrl(@PathVariable("key") String key , HttpServletResponse response) throws IOException{
        log.info("Getting Url Value based on the user key started");
             URL originalUrl = service.findoriginalURL(key);
      // log.info("--"+originalUrl.getFullUrl()+"---");
             return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl.getFullUrl())).build();
    }
             
}

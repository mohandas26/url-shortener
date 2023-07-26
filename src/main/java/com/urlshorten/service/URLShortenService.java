package com.urlshorten.service;


import com.urlshorten.entity.URL;
import com.urlshorten.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;

@Service
public class URLShortenService {

    @Autowired
    URLRepository repo;
    @Autowired
    BaseConversions baseConversions;
    
    Logger log = Logger.getLogger(URLShortenService.class.getName());


    public String createShortURL(String userURL){
        Optional<URL> getUrl = repo.findByFullUrl(userURL);
            if (getUrl.isEmpty()){
                URL url = new URL();
                url.setFullUrl(userURL);
                repo.save(url);
                log.info("Values inserted");
            }

            return baseConversions.encode(repo.findByFullUrl(userURL).get());

    }

    public URL findoriginalURL(String key){
        Integer id = baseConversions.decode(key);
        log.info(id+" -> Id");
        URL url = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no url mapped with " + key));
        log.info("Retrieved URL");
        return url;
    }
}

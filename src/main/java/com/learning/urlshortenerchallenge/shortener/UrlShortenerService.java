package com.learning.urlshortenerchallenge.shortener;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.urlshortenerchallenge.exceptions.UrlExpiredException;
import com.learning.urlshortenerchallenge.exceptions.UrlNotFoundException;
import com.learning.urlshortenerchallenge.model.Url;
import com.learning.urlshortenerchallenge.model.UrlDTO;
import com.learning.urlshortenerchallenge.repository.UrlRepository;

@Service
public class UrlShortenerService {

    @Autowired
    private UrlShortener urlShortener;

    @Autowired
    private UrlRepository urlRepository;

    public UrlDTO shortenUrl(String rawUrl) {
        Url url = this.urlShortener.shortenUrl(rawUrl);
        url.setExpirationDate(LocalDate.now().plusDays(7));
        this.urlRepository.save(url);

        return new UrlDTO(url.getShortenedUrl());
    }

    public UrlDTO getByShortenedUrl(String shortenedUrl) {
        Url fetched = this.urlRepository.findByShortenedUrl(shortenedUrl)
        								.orElseThrow(() -> 
        									new UrlNotFoundException("The requested URL cannot be found."));
        
        if(fetched.getExpirationDate().isBefore(LocalDate.now()))
        	throw new UrlExpiredException("The requested URL is expired");
        
        return new UrlDTO(fetched.getRawUrl());
    }

}

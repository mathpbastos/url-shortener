package com.learning.urlshortenerchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.urlshortenerchallenge.model.UrlDTO;
import com.learning.urlshortenerchallenge.shortener.UrlShortenerService;

import jakarta.validation.Valid;

@RestController
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("shorten-url")
    public ResponseEntity<UrlDTO> shortenUrl(@RequestBody @Valid UrlDTO urlDTO) {
        UrlDTO saved = this.urlShortenerService.shortenUrl(urlDTO.url());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("get-raw-url")
    public ResponseEntity<UrlDTO> getRawUrl(@RequestBody UrlDTO urlDTO) {
        if(urlDTO.url() == null || urlDTO.url().isEmpty())
            return ResponseEntity.badRequest().build();

        UrlDTO fetched = this.urlShortenerService.getByShortenedUrl(urlDTO.url());

        return ResponseEntity.ok(fetched);
    }

}

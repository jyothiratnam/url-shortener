package com.example.urlshortener.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.urlshortener.service.UrlService;

@RestController
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {

        String longUrl = urlService.getLongUrl(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(longUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
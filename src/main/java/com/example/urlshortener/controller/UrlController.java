package com.example.urlshortener.controller;

import com.example.urlshortener.dto.CreateUrlRequest;
import com.example.urlshortener.dto.CreateUrlResponse;
import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<CreateUrlResponse> save( @Valid  @RequestBody CreateUrlRequest request) {
        CreateUrlResponse response = urlService.createShortUrl(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * This wont work becuase to call this we need end point as /api/v1/urls/shortcode
     * but this is not feasible so created RedirectController
     * @param shortCode
     * @return
     */
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode) {
        System.out.println("Shortcode: " + shortCode);

        String longUrl = urlService.getLongUrl(shortCode);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }

}

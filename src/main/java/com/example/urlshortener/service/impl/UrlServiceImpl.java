package com.example.urlshortener.service.impl;

import com.example.urlshortener.dto.CreateUrlRequest;
import com.example.urlshortener.dto.CreateUrlResponse;
import com.example.urlshortener.entity.UrlMapping;
import com.example.urlshortener.repository.UrlRepository;
import com.example.urlshortener.service.UrlService;
import com.example.urlshortener.util.Base62Util;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;


    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
    @Override
    public String getLongUrl(String shortCode) {

        UrlMapping url = urlRepository.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));

        if (!url.getActive()) {
            throw new RuntimeException("URL is inactive");
        }

        if (url.getExpiresAt() != null &&
                url.getExpiresAt().isBefore(LocalDateTime.now())) {

            throw new RuntimeException("URL expired");
        }

        url.setClickCount(url.getClickCount() + 1);

        urlRepository.save(url);

        return url.getLongUrl();
    }
    @Override
    public CreateUrlResponse createShortUrl(CreateUrlRequest request) {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setLongUrl(request.getLongUrl());
        urlMapping.setExpiresAt(
                LocalDateTime.now().plusYears(1)
        );
        urlMapping=urlRepository.save(urlMapping);
        String shortCode = Base62Util.encode(urlMapping.getId());
        urlMapping.setShortUrl(shortCode);
        urlRepository.save(urlMapping);

        return new CreateUrlResponse("http://localhost:8080/"+shortCode);
    }
}

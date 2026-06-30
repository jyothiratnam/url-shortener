package com.example.urlshortener.service;

import com.example.urlshortener.dto.CreateUrlRequest;
import com.example.urlshortener.dto.CreateUrlResponse;

public interface UrlService {
    CreateUrlResponse createShortUrl(CreateUrlRequest request);
    String getLongUrl(String shortCode);
}

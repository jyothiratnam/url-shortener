package com.example.urlshortener.dto;

public class CreateUrlResponse {
    private String shortUrl;

    public CreateUrlResponse() {
    }

    public CreateUrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}

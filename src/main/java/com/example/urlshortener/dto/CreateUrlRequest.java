package com.example.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateUrlRequest {

    @NotBlank
    private String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
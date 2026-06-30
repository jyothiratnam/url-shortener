package com.example.urlshortener.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "url_mapping")
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "Text")
    private String longUrl;

    @Column(unique = true, length = 10)
    private String shortUrl;

    private Long clickCount=0L;

    private Boolean active=true;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
    public UrlMapping() {}

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}

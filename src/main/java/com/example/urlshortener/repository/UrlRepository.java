package com.example.urlshortener.repository;

import java.util.Optional;

import com.example.urlshortener.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UrlRepository extends JpaRepository<UrlMapping, Long>{

    Optional<UrlMapping> findByShortUrl(String shortCode);

}
package com.bukharov.url_shortener.app_service.services;

public interface UrlService {
	String shortenUrl(String originalUrl);
	String getOriginalUrl(String shortUrl);
}
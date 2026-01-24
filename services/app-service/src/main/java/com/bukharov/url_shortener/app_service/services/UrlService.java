package com.bukharov.url_shortener.app_service.services;

public interface UrlService {
	String saveUniqueCode(String originalUrl);
	String getByUniqueCode(String uniqueCode);
}
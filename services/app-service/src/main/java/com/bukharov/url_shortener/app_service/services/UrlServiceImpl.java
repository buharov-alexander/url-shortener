package com.bukharov.url_shortener.app_service.services;

import org.springframework.stereotype.Service;

@Service
class UrlServiceImpl implements UrlService {
	@Override
	public String shortenUrl(String originalUrl) {
		return originalUrl;
	}

	@Override
	public String getOriginalUrl(String shortUrl) {
		return shortUrl;
	}
}
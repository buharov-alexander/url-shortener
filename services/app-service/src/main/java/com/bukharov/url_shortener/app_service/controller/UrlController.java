package com.bukharov.url_shortener.app_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.bukharov.url_shortener.app_service.services.UrlService;

@RestController
@RequestMapping("/url")
public class UrlController {
	
	@Autowired
	private UrlService urlService;

	@PostMapping("/shorten")
	public UrlDTO shortenUrl(@RequestBody UrlDTO urlDTO) {
		return new UrlDTO(urlService.shortenUrl(urlDTO.url()));
	}

	@GetMapping("/{shortCode}")
	public UrlDTO redirectToOriginalUrl(@PathVariable String shortCode) {
		return new UrlDTO(urlService.getOriginalUrl(shortCode));
	}
}

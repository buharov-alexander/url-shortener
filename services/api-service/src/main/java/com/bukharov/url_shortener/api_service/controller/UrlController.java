package com.bukharov.url_shortener.api_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/url")
public class UrlController {
	
	@PostMapping("/shorten")
	public UrlDTO shortenUrl(@RequestBody UrlDTO urlDTO) {
		return new UrlDTO("shortened_url");
	}

	@GetMapping("/{shortCode}")
	public UrlDTO redirectToOriginalUrl() {
		return new UrlDTO("original_url");
	}
}

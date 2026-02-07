package com.bukharov.url_shortener.app_service.controller;

import java.net.URI;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@PostMapping
	public UrlDTO shortenUrl(@RequestBody UrlDTO urlDTO, HttpServletRequest request) {
		String uniqueCode = urlService.saveUniqueCode(urlDTO.url());
		String shortenUrl = String.format("%s/%s", request.getRequestURL(), uniqueCode);
		return new UrlDTO(shortenUrl);
	}

	@GetMapping("/{uniqueCode}")
	public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String uniqueCode) {
		return ResponseEntity
				.status(HttpStatus.MOVED_PERMANENTLY)
				.location(URI.create(urlService.getByUniqueCode(uniqueCode)))
				.build();
	}
}

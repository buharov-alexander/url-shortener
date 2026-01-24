package com.bukharov.url_shortener.app_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UrlServiceImpl implements UrlService {

	@Autowired
	private IdGeneratorService idGeneratorService;

	@Override
	public String saveUniqueCode(String originalUrl) {
		return idGeneratorService.generateUniqueCode();
	}

	@Override
	public String getByUniqueCode(String uniqueCode) {
		return uniqueCode;
	}
}
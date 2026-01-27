package com.bukharov.url_shortener.app_service.services;

import com.bukharov.url_shortener.app_service.repository.UrlEntity;
import com.bukharov.url_shortener.app_service.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UrlServiceImpl implements UrlService {

	@Autowired
	private IdGeneratorService idGeneratorService;
	@Autowired
	private UrlRepository urlRepository;

	@Override
	public String saveUniqueCode(String originalUrl) {
		String uniqueCode = idGeneratorService.generateUniqueCode();
		UrlEntity entity = new UrlEntity();
		entity.setOriginalUrl(originalUrl);
		entity.setUniqueCode(uniqueCode);
		urlRepository.save(entity);
		return uniqueCode;
	}

	@Override
	public String getByUniqueCode(String uniqueCode) {
		return uniqueCode;
	}
}
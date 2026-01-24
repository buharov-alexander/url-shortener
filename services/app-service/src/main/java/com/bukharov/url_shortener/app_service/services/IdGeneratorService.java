package com.bukharov.url_shortener.app_service.services;

import org.springframework.stereotype.Service;

@Service
public class IdGeneratorService {

	private final Base62 base62;
	private final Snowflake snowflake;

	private static final String HOSTNAME_ENV = "HOSTNAME";
	public IdGeneratorService() {
		this.base62 = new Base62();
		this.snowflake = new Snowflake(getNodeId());
	}

	public String generateUniqueCode() {
		// Generate Snowflake ID
		long snowflakeId = snowflake.nextId();
		
		// Encode to Base62
		return base62.encode(snowflakeId);
	}

	private static long getNodeId() {
		String hostname = System.getProperty(HOSTNAME_ENV, "0");
        String digits = hostname.replaceAll("\\D", "");
        return Long.parseLong(digits);
	}
}
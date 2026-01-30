package com.bukharov.url_shortener.app_service;

import com.bukharov.url_shortener.app_service.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.cassandra.autoconfigure.CassandraAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
		CassandraAutoConfiguration.class,
})
class AppServiceApplicationTests {

	@MockitoBean
	private UrlRepository urlRepository;

	@Test
	void contextLoads() {
	}

}

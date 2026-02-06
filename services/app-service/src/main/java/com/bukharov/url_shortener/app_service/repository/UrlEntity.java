package com.bukharov.url_shortener.app_service.repository;

import java.util.UUID;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table("urls")
public class UrlEntity {

	@PrimaryKey
	private String uniqueCode;
	private String originalUrl;
}

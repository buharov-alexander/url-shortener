/***************       BEGIN-STANDARD-COPYRIGHT      ***************

 Copyright (c) 2009-2026, Spirent Communications.

 All rights reserved. Proprietary and confidential information of Spirent Communications.
 ***************        END-STANDARD-COPYRIGHT       ***************/
package com.bukharov.url_shortener.app_service.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UrlRepository extends CassandraRepository<UrlEntity, UUID> {
}

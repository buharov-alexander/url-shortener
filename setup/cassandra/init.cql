CREATE KEYSPACE IF NOT EXISTS url_keyspace 
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE IF NOT EXISTS url_keyspace.urls (
	uniqueCode text PRIMARY KEY,
	originalUrl text,
);

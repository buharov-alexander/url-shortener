CREATE KEYSPACE IF NOT EXISTS url_keyspace 
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE IF NOT EXISTS url_keyspace.urls (
	id uuid PRIMARY KEY,
	originalUrl text,
	uniqueCode text,
);

#!/bin/bash

cd services
mvn clean install

docker build -t buharovalexander/url-shortener-app-service:latest ./app-service

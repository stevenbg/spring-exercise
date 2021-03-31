# Spring Boot exercise

## Run

`run-docker-mysql`

`mvnw flyway:migrate`

`mvnw spring-boot:run`

## Usage

`curl -i http://localhost:8080/v1/burgers -X POST -H "Content-Type: application/json" -d "{\"name\":\"Big Mac\"}"`

`curl -i http://localhost:8080/v1/burgers`

`curl -i http://localhost:8080/v1/burgers/1`

`curl -i http://localhost:8080/v1/burgers/random`

`curl -i "http://localhost:8080/v1/burgers?page=2&per_page=2&name=big"`

`curl -i http://localhost:8080/v1/burgers/1 -X DELETE`

## Todo
- [x] init
- [x] endpoint stubs
- [x] class burger
- [x] endpoint mocks
- [x] tests
- [x] burger repo
- [x] crud endpoints
- [x] db storage
- [x] list search, pagination
- [x] config
- [x] doc
- [x] rate limit middleware
- [x] db migrations
- [x] prod/dev config by env
- [x] n:m entities  
- [ ] packaging options
- [ ] cli
- [ ] better errors
- [ ] better repository
- [ ] dig in hibernate  
- [ ] dto mapper

Directory structure akin to https://github.com/spring-io/sagan/tree/master/sagan-site/src/main/java/sagan/site

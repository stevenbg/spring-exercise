# Spring Boot exercise

## Run

`run-docker-mysql`

`mvnw flyway:migrate`

`mvnw spring-boot:run`

## Usage

`curl -i http://localhost:40080/v1/burgers -X POST -H "Content-Type: application/json" -d "{\"name\":\"Big Mac\"}"`

`curl -i http://localhost:40080/v1/burgers`

`curl -i http://localhost:40080/v1/burgers/1`

`curl -i http://localhost:40080/v1/burgers/random`

`curl -i "http://localhost:40080/v1/burgers?page=2&per_page=2&name=big"`

`curl -i http://localhost:40080/v1/burgers/1 -X DELETE`

`curl -i http://localhost:40080/v1/ingredients`

`curl -i http://localhost:40080/v1/burgers/2/ingredients -X POST -H "Content-Type: application/json" -d "[1,3,5]"`

## Todo
- [x] init
- [x] endpoint stubs
- [x] class burger
- [x] endpoint mocks
- [x] test
- [x] burger repo
- [x] crud endpoints
- [x] db storage
- [x] list search, pagination
- [x] config
- [x] doc
- [x] rate limit middleware
- [x] db migrations
- [x] prod/dev config by env
- [x] n:m entity
- [x] metamodels  
- [x] dto mapper 
- [ ] hibernate versioning  
- [ ] packaging options
- [ ] cli
- [ ] better errors
- [ ] better repository

Directory structure akin to https://github.com/spring-io/sagan/tree/master/sagan-site/src/main/java/sagan/site

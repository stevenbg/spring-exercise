# Spring Boot exercise

## Basic
- [x] init
- [x] endpoint stubs
- [x] class burger
- [x] endpoint mocks
- [ ] tests
- [x] burger repo
- [x] crud endpoints
- [ ] db storage
- [ ] db migrations
- [x] list search, pagination
- [ ] config, prod/dev
- [x] doc
- [x] rate limit middleware

## Extra
- [ ] packaging options
- [ ] cli
- [ ] better errors
- [ ] better repository
- [ ] related entity
- [ ] dto mapper

`curl -i http://localhost:8080/v1/burgers -X POST -H "Content-Type: application/json" -d "{\"name\":\"Big Mac\"}"`

`curl -i http://localhost:8080/v1/burgers/1`

`curl -i http://localhost:8080/v1/burgers/random`

`curl -i "http://localhost:8080/v1/burgers?page=2&per_page=2&name=big"`

`curl -i http://localhost:8080/v1/burgers/1 -X DELETE`

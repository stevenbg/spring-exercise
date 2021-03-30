#!/usr/bin/env bash

wehere=$(cd "$(dirname "${BASH_SOURCE[0]}")" || exit; pwd -P)

docker run -d -p 127.0.0.1:3306:3306 \
  -v "${wehere}dev/initdb.sql:/docker-entrypoint-initdb.d/initdb.sql" \
  -v "dbdata:/var/lib/mysql" \
  mysql/mysql-server:8.0

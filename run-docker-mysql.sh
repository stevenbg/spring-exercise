#!/usr/bin/env bash

wehere=$(cd "$(dirname "${BASH_SOURCE[0]}")" || exit; pwd -P)

sudo podman run --name=mysql --rm -d -p 127.0.0.1:43306:3306 \
  -v "${wehere}/dev/initdb.sql:/docker-entrypoint-initdb.d/initdb.sql" \
  -v "/var/lib/mysql" \
  mysql/mysql-server:8.0

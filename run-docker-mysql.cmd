docker run --name=mysql --rm -d -p 127.0.0.1:3306:3306 -v "%~dp0dev/initdb.sql:/docker-entrypoint-initdb.d/initdb.sql" -v "/var/lib/mysql" mysql/mysql-server:8.0

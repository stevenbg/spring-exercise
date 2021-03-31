CREATE DATABASE myrest;
CREATE USER 'myrest'@'%' IDENTIFIED BY 'myrest';
GRANT ALL ON myrest.* TO 'myrest'@'%';

CREATE USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root';
GRANT ALL ON *.* TO 'root'@'%';

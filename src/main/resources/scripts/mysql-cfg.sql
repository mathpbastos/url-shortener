DROP DATABASE IF EXISTS urlshortenerdb;
DROP USER IF EXISTS `shorteneradmin`@`%`;
DROP USER IF EXISTS `shorteneruser`@`%`;
CREATE DATABASE IF NOT EXISTS urlshortenerdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `shorteneradmin`@`%` IDENTIFIED WITH mysql_native_password BY 'letmein';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `urlshortenerdb`.* TO `shorteneradmin`@`%`;
CREATE USER IF NOT EXISTS `shorteneruser`@`%` IDENTIFIED WITH mysql_native_password BY 'letmein';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `urlshortenerdb`.* TO `shorteneruser`@`%`;
FLUSH PRIVILEGES;
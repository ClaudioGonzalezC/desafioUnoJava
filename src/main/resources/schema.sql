CREATE SCHEMA IF NOT EXISTS testJpa_agenda_db;
USE testJpa_agenda_db;

CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    sku VARCHAR(100) NOT NULL,
    precio_normal DOUBLE NOT NULL,
    precio_oferta DOUBLE NOT NULL,
    imagen VARCHAR(255),
    disponibilidad VARCHAR(50)
);
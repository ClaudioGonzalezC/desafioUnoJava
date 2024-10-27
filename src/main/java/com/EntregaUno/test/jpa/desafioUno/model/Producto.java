package com.EntregaUno.test.jpa.desafioUno.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String sku;

    private Double precioNormal;

    private Double precioOferta;

    private String imagen;

    @Enumerated(EnumType.STRING)
    private Disponibilidad disponibilidad;

    private Integer stock;

    private String descripcion;

    // Este es el constructor original simplificado que ya tenías
    public Producto(Long id, String nombre, Double precioNormal, Integer stock, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precioNormal = precioNormal;
        this.stock = stock;
        this.descripcion = descripcion;
    }

    // Este es el constructor adicional solicitado con los parámetros requeridos
    public Producto(Long id, String nombre, Double precioNormal, Double precioOferta, String descripcion, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioNormal = precioNormal;
        this.precioOferta = precioOferta;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    // Otros métodos...
}
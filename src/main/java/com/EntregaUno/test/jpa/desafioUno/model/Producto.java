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

    public Producto(Long id, String nombre, Double precioNormal, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioNormal = precioNormal;
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

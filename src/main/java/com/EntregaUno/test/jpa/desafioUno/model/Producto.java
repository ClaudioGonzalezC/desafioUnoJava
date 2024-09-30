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
}
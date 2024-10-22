package com.EntregaUno.test.jpa.desafioUno.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa un producto")
public class ProductoDTO {

    @Schema(description = "ID único del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Televisor")
    private String nombre;

    @Schema(description = "Descripción del producto", example = "Televisor de alta definición")
    private String descripcion;

    @Schema(description = "Cantidad disponible del producto", example = "100")
    private Integer cantidad;

    @Schema(description = "Precio normal del producto", example = "499.99")
    private Double precioNormal;

    @Schema(description = "Precio de oferta del producto", example = "399.99")
    private Double precioOferta;

    @Schema(description = "Descuento del producto", example = "10.0")
    private Double descuento;

    @Schema(description = "Categoría del producto", example = "Electrónica")
    private String categoria;

    @Schema(description = "Stock disponible", example = "50")
    private Integer stock;

    public Double getPrecio() {
        return this.precioOferta != null ? this.precioOferta : 
               (this.precioNormal != null ? this.precioNormal : 0.0);
    }

    public ProductoDTO(Long id, String nombre, String descripcion, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public ProductoDTO(Long id, String nombre, Double precioNormal, Double precioOferta, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precioNormal = precioNormal;
        this.precioOferta = precioOferta;
        this.cantidad = cantidad;
    }

    public ProductoDTO(Long id, String nombre, Double precio, Double descuento, String categoria, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioNormal = precio;
        this.descuento = descuento;
        this.categoria = categoria;
        this.stock = stock;
    }

    public ProductoDTO(Long id, String nombre, Double precio, String categoria, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioNormal = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.descuento = null;
    }
}

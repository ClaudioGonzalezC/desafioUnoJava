package com.EntregaUno.test.jpa.desafioUno.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
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

    // Campos adicionales
    @Schema(description = "Descuento del producto", example = "10.0")
    private Double descuento;

    @Schema(description = "Categoría del producto", example = "Electrónica")
    private String categoria;

    @Schema(description = "Stock disponible", example = "50")
    private Integer stock;

    // Lógica para obtener el precio final (normal o oferta)
    public Double getPrecio() {
        return this.precioOferta != null ? this.precioOferta : 
               (this.precioNormal != null ? this.precioNormal : 0.0);
    }

    // Constructor parcial sin los campos de precio (normal y oferta)
    public ProductoDTO(Long id, String nombre, String descripcion, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    // Constructor sin el campo descripcion (Long, String, Double, Double, Integer)
    public ProductoDTO(Long id, String nombre, Double precioNormal, Double precioOferta, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precioNormal = precioNormal;
        this.precioOferta = precioOferta;
        this.cantidad = cantidad;
    }

    // Constructor con los nuevos campos (precio, descuento, categoría, stock)
    public ProductoDTO(Long id, String nombre, Double precio, Double descuento, String categoria, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioNormal = precio; // Usamos precioNormal como el campo que se refiere a precio
        this.descuento = descuento;
        this.categoria = categoria;
        this.stock = stock;
    }

    // Constructor sin descuento (precio, categoría, stock)
    public ProductoDTO(Long id, String nombre, Double precio, String categoria, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precioNormal = precio; // Usamos precioNormal como el campo que se refiere a precio
        this.categoria = categoria;
        this.stock = stock;
        this.descuento = null;  // Descuento opcional o valor por defecto
    }
}

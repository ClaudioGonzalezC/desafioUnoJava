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

    @Schema(description = "Precio del producto", example = "499.99")
    private Double precio;

    @Schema(description = "Cantidad disponible del producto", example = "100")
    private Integer cantidad;
}

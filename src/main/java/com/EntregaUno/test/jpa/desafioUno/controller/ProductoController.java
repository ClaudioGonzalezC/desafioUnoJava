package com.EntregaUno.test.jpa.desafioUno.controller;

import com.EntregaUno.test.jpa.desafioUno.dto.ProductoDTO;
import com.EntregaUno.test.jpa.desafioUno.dto.UserDTO;
import com.EntregaUno.test.jpa.desafioUno.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Obtener todos los productos", description = "Retorna una lista de todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    @GetMapping
    public List<ProductoDTO> getAllProductos() {
        return productoService.getAllProductos();
    }

    @Operation(summary = "Obtener un producto por ID", description = "Retorna un producto específico por su ID")
    @ApiResponse(responseCode = "200", description = "Producto obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @GetMapping("/{id}")
    public ProductoDTO getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto con los detalles proporcionados")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    @PostMapping
    public ProductoDTO createProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.saveProducto(productoDTO);
    }

    @Operation(summary = "Actualizar un producto", description = "Actualiza los detalles de un producto existente")
    @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @PutMapping("/{id}")
    public ProductoDTO updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO existingProducto = productoService.getProductoById(id);
        if (existingProducto != null) {
            productoDTO.setId(id);
            return productoService.saveProducto(productoDTO);
        }
        throw new RuntimeException("Producto no encontrado con id: " + id);
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto específico por su ID")
    @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }

    @Operation(summary = "Obtener usuarios desde API externa", description = "Obtiene una lista de usuarios desde una API externa")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = productoService.getUsersFromExternalApi();
        return ResponseEntity.ok(users);
    }
}

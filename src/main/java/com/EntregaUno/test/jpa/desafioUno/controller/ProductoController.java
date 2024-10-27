package com.EntregaUno.test.jpa.desafioUno.controller;

import com.EntregaUno.test.jpa.desafioUno.dto.ProductoDTO;
import com.EntregaUno.test.jpa.desafioUno.dto.UserDTO;
import com.EntregaUno.test.jpa.desafioUno.service.ProductoServiceImpl;
import com.EntregaUno.test.jpa.desafioUno.utils.ApiResponseMsg; // Asegúrate de importar ApiResponseMsg
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
    private ProductoServiceImpl productoService;

    @Operation(summary = "Obtener todos los productos", description = "Retorna una lista de todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    @GetMapping
    public ResponseEntity<ApiResponseMsg> getAllProductos() {
        List<ProductoDTO> productos = productoService.getAllProductos();
        return ResponseEntity.ok(new ApiResponseMsg("Lista de productos obtenida exitosamente", productos));
    }

    @Operation(summary = "Obtener un producto por ID", description = "Retorna un producto específico por su ID")
    @ApiResponse(responseCode = "200", description = "Producto obtenido exitosamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMsg> getProductoById(@PathVariable Long id) {
        ProductoDTO producto = productoService.getProductoById(id);
        if (producto != null) {
            return ResponseEntity.ok(new ApiResponseMsg("Producto obtenido exitosamente", producto));
        } else {
            return ResponseEntity.status(404).body(new ApiResponseMsg("Producto no encontrado", null));
        }
    }

    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto con los detalles proporcionados")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    @PostMapping
    public ResponseEntity<ApiResponseMsg> createProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO savedProducto = productoService.saveProducto(productoDTO);
        return ResponseEntity.status(201).body(new ApiResponseMsg("Producto creado exitosamente", savedProducto));
    }

    @Operation(summary = "Actualizar un producto", description = "Actualiza los detalles de un producto existente")
    @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseMsg> updateProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO existingProducto = productoService.getProductoById(id);
        if (existingProducto != null) {
            productoDTO.setId(id);
            ProductoDTO updatedProducto = productoService.saveProducto(productoDTO);
            return ResponseEntity.ok(new ApiResponseMsg("Producto actualizado exitosamente", updatedProducto));
        }
        return ResponseEntity.status(404).body(new ApiResponseMsg("Producto no encontrado con id: " + id, null));
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto específico por su ID")
    @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMsg> deleteProducto(@PathVariable Long id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.ok(new ApiResponseMsg("Producto eliminado exitosamente", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponseMsg("Producto no encontrado", null));
        }
    }

    @Operation(summary = "Obtener usuarios desde API externa", description = "Obtiene una lista de usuarios desde una API externa")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente")
    @GetMapping("/users")
    public ResponseEntity<ApiResponseMsg> getUsers() {
        List<UserDTO> users = productoService.getUsersFromExternalApi();
        return ResponseEntity.ok(new ApiResponseMsg("Lista de usuarios obtenida exitosamente", users));
    }
}
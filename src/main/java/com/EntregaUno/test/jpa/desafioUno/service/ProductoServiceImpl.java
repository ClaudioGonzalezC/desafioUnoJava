package com.EntregaUno.test.jpa.desafioUno.service;

import com.EntregaUno.test.jpa.desafioUno.mapper.ProductoMapper;
import com.EntregaUno.test.jpa.desafioUno.dto.ProductoDTO;
import com.EntregaUno.test.jpa.desafioUno.dto.UserDTO;
import com.EntregaUno.test.jpa.desafioUno.exception.ResourceNotFoundException;
import com.EntregaUno.test.jpa.desafioUno.model.Producto;
import com.EntregaUno.test.jpa.desafioUno.repository.ProductoRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper; // Inyección del mapper


    @Override
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll()
            .stream()
            .map(productoMapper::toDTO) 
            .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        return productoMapper.toDTO(producto);
    }

    @Override
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        if (productoDTO.getPrecio() < 0) {
            throw new RuntimeException("El precio del producto no puede ser negativo");
        }
        Producto producto = productoMapper.toEntity(productoDTO);
        producto = productoRepository.save(producto);
        return productoMapper.toDTO(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        productoRepository.delete(producto);
    }

    @Override
    public void validarStock(List<ProductoDTO> productosDTO) {
        productosDTO.forEach(productoDTO -> {
            Producto productoExistente = productoRepository.findById(productoDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + productoDTO.getId()));
            if (productoDTO.getCantidad() > productoExistente.getStock()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + productoExistente.getNombre());
            }
        });
    }

    @Override
    public void reducirStock(List<ProductoDTO> productosDTO) {
        productosDTO.forEach(productoDTO -> {
            Producto productoExistente = productoRepository.findById(productoDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + productoDTO.getId()));
            productoExistente.setStock(productoExistente.getStock() - productoDTO.getCantidad());
            productoRepository.save(productoExistente);
        });
    }

    @Override
    public List<UserDTO> getUsersFromExternalApi() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://jsonplaceholder.typicode.com/users";
        UserDTO[] usersArray = restTemplate.getForObject(apiUrl, UserDTO[].class);
        return usersArray != null ? List.of(usersArray) : List.of();
    }

    @Override
    public List<UserDTO> getUsersFromExternalApiUsingHttpClient() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            List<UserDTO> users = gson.fromJson(response.body(), new TypeToken<List<UserDTO>>(){}.getType());

            return users;
        } catch (Exception e) {
            throw new RuntimeException("Error al consumir la API externa", e);
        }
    }
}
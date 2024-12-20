package com.EntregaUno.test.jpa.desafioUno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.EntregaUno.test.jpa.desafioUno.dto.ProductoDTO;
import com.EntregaUno.test.jpa.desafioUno.service.ProductoServiceImpl;

@SpringBootApplication
public class DesafioUnoApplication implements CommandLineRunner {

    @Autowired
    ProductoServiceImpl productoService;

    public static void main(String[] args) {
        SpringApplication.run(DesafioUnoApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Mostrando Productos:");

        for (ProductoDTO p : productoService.getAllProductos()) {
            System.out.println(p);
        }
    }
}

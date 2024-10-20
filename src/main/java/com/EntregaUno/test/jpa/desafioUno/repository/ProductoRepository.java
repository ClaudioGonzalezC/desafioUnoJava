package com.EntregaUno.test.jpa.desafioUno.repository;

import com.EntregaUno.test.jpa.desafioUno.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

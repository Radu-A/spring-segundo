package com.unaempresa.segundoproyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unaempresa.segundoproyecto.entity.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	
    // Query method derivado: busca clientes cuyo nombre contenga el texto,
    // ignorando mayúsculas y minúsculas.
    // SQL generado: SELECT * FROM clientes WHERE LOWER(nombre) LIKE LOWER(%nombre%)
	List<Cliente> findByNombreContainingIgnoreCase(String nombre);
}
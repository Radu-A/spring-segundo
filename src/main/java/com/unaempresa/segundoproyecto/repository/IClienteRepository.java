package com.unaempresa.segundoproyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unaempresa.segundoproyecto.entity.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	
    // Query method derivado: busca clientes cuyo nombre contenga el texto,
    // ignorando mayúsculas y minúsculas.
    // SQL generado: SELECT * FROM clientes WHERE LOWER(nombre) LIKE LOWER(%nombre%)
	List<Cliente> findByNombreContainingIgnoreCase(String nombre);
	
	// JPQL: buscar clientes cuyo email pertenezca a un dominio determinado.
    // 'c' es el alias de la entidad Cliente en la query.
    // c.email hace referencia al campo Java, no a la columna de la BD.
	@Query("SELECT c FROM Cliente c WHERE c.email LIKE %:dominio%")
	List<Cliente> findByDominioEmail(@Param("dominio") String dominio);
}
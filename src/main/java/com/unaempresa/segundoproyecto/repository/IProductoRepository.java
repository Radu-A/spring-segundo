package com.unaempresa.segundoproyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unaempresa.segundoproyecto.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
	
    // Búsqueda parcial de nombre (LIKE), insensible a mayúsculas
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}

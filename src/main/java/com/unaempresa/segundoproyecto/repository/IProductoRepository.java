package com.unaempresa.segundoproyecto.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unaempresa.segundoproyecto.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

	// Búsqueda parcial de nombre (LIKE), insensible a mayúsculas
	List<Producto> findByNombreContainingIgnoreCase(String nombre);

	// JPQL: productos con precio menor o igual al máximo, ordenados de más barato a más caro.
    @Query("SELECT p FROM Producto p WHERE p.precio <= :maxPrecio ORDER BY p.precio ASC")
    List<Producto> findProductosHastaPrecio(@Param("maxPrecio") BigDecimal maxPrecio);
}

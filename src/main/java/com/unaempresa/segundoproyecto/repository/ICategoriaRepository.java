package com.unaempresa.segundoproyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unaempresa.segundoproyecto.entity.Categoria;
import com.unaempresa.segundoproyecto.entity.Producto;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
	
	// Añade un query method que busque categorías por nombre (ignorando mayúsculas)
	List<Categoria> findByNombreContainingIgnoreCase(String categoria);
	// Añade una @Query JPQL que devuelva todos los Producto de una categoría dada su id
	@Query("SELECT p FROM Categoria c JOIN c.productos p WHERE c.id = :id")
	List<Producto> findProductosByCategoriaId(@Param("id") Long id);
	
}

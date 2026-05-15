package com.unaempresa.segundoproyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unaempresa.segundoproyecto.entity.Articulo;

@Repository
public interface IArticuloRepository extends JpaRepository<Articulo, Long> {
}

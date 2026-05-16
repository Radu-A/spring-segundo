package com.unaempresa.segundoproyecto.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unaempresa.segundoproyecto.entity.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

	// Navega la relación @ManyToOne: equivale a WHERE cliente_id = ?
	List<Pedido> findByClienteId(Long clienteId);

	// Filtra por estado: PENDIENTE, COMPLETADO o CANCELADO
	List<Pedido> findByEstado(String estado);

	// JPQL: navega la relación @ManyToOne con notación de objeto.
    // p.cliente.email navega el grafo de objetos sin JOIN explícito.
    @Query("SELECT p FROM Pedido p WHERE p.cliente.email = :email")
    List<Pedido> findByClienteEmail(@Param("email") String email);

    // JPQL con ORDER BY: pedidos con total >= minTotal, ordenados de mayor a menor.
    @Query("SELECT p FROM Pedido p WHERE p.total >= :minTotal ORDER BY p.total DESC")
    List<Pedido> findPedidosConTotalMinimo(@Param("minTotal") BigDecimal minTotal);
}

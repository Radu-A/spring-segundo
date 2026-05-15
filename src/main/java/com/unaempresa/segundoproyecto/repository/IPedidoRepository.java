package com.unaempresa.segundoproyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unaempresa.segundoproyecto.entity.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

    // Navega la relación @ManyToOne: equivale a WHERE cliente_id = ?
    List<Pedido> findByClienteId(Long clienteId);

    // Filtra por estado: PENDIENTE, COMPLETADO o CANCELADO
    List<Pedido> findByEstado(String estado);
}

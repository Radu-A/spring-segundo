package com.unaempresa.segundoproyecto.service;

import java.util.List;

import com.unaempresa.segundoproyecto.entity.Pedido;
import com.unaempresa.segundoproyecto.repository.IPedidoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoServiceImpl implements IPedidoService {

    private final IPedidoRepository pedidoRepository;

    public PedidoServiceImpl(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> findByClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> findByEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }


}

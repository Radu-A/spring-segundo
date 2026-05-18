package com.unaempresa.segundoproyecto.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.unaempresa.segundoproyecto.entity.Cliente;
import com.unaempresa.segundoproyecto.entity.Pedido;
import com.unaempresa.segundoproyecto.entity.Producto;
import com.unaempresa.segundoproyecto.exception.ClienteNoEncontradoException;
import com.unaempresa.segundoproyecto.exception.PedidoNoEncontradoException;
import com.unaempresa.segundoproyecto.exception.ProductoNoEncontradoException;
import com.unaempresa.segundoproyecto.repository.IClienteRepository;
import com.unaempresa.segundoproyecto.repository.IPedidoRepository;
import com.unaempresa.segundoproyecto.repository.IProductoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PedidoServiceImpl implements IPedidoService {
	
    private final IPedidoRepository pedidoRepository;
    private final IClienteRepository clienteRepository;
    private final IProductoRepository productoRepository;

    public PedidoServiceImpl(IPedidoRepository pedidoRepository, IClienteRepository clienteRepository,
			IProductoRepository productoRepository) {
		this.pedidoRepository = pedidoRepository;
		this.clienteRepository = clienteRepository;
		this.productoRepository = productoRepository;
	}

	@Override
    @Transactional(readOnly = true)
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(()->new PedidoNoEncontradoException(id));
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

    @Override
    public Pedido crearPedidoConProductos(Long clienteId, List<Long> productoIds,
    		String estado, BigDecimal total) {
    	
    	// Buscamos cliente
    	Cliente cliente = clienteRepository.findById(clienteId)
    			.orElseThrow(()->new ClienteNoEncontradoException(clienteId));
    	
    	// Buscamos los productos
    	List<Producto> productos = productoIds.stream()
                .map(id -> productoRepository.findById(id)
                        .orElseThrow(() -> new ProductoNoEncontradoException(id)))
                .collect(Collectors.toList());
    	Pedido pedido = new Pedido();
    	pedido.setCliente(cliente);
    	pedido.setProductos(productos);
    	pedido.setFecha(LocalDate.now());
    	pedido.setEstado(estado);
    	pedido.setTotal(total);
    	return pedidoRepository.save(pedido);
    }
}

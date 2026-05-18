package com.unaempresa.segundoproyecto.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unaempresa.segundoproyecto.entity.Producto;
import com.unaempresa.segundoproyecto.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {
	
    private final IProductoRepository productoRepository;

    public ProductoServiceImpl(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombre(String nombre) {
    	List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(nombre);
    	System.out.println("Productos en el service: " + productos);
        return productos;
    }
}

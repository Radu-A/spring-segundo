package com.unaempresa.segundoproyecto.service;

import java.util.List;

import com.unaempresa.segundoproyecto.entity.Producto;

public interface IProductoService {
	
	List<Producto> findAll();
	Producto findById(Long id);
	void deleteById(Long id);
	Producto save(Producto producto);
	List<Producto> findByNombre(String nombre);
}

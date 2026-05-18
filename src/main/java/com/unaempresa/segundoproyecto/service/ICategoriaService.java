package com.unaempresa.segundoproyecto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unaempresa.segundoproyecto.entity.Categoria;
import com.unaempresa.segundoproyecto.entity.Producto;

public interface ICategoriaService {
	
	List<Categoria> findAll();
	Page<Categoria> findAllPaginado(Pageable pageable);
	Categoria findById(Long id);
	void deleteById(Long id);
	Categoria save(Categoria categoria);
	List<Producto> findProductosByCategoriaId(Long categoriaId);
}

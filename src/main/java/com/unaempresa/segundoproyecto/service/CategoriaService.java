package com.unaempresa.segundoproyecto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unaempresa.segundoproyecto.entity.Categoria;
import com.unaempresa.segundoproyecto.entity.Producto;
import com.unaempresa.segundoproyecto.repository.ICategoriaRepository;

@Service
@Transactional
public class CategoriaService implements ICategoriaService {
	
	private ICategoriaRepository categoriaRepository;
	
	public CategoriaService(ICategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Categoria> findAllPaginado(Pageable pageable) {
		return categoriaRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		categoriaRepository.deleteById(id);
	}

	@Override
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findProductosByCategoriaId(Long categoriaId) {
		return categoriaRepository.findProductosByCategoriaId(categoriaId);
	}

}

package com.unaempresa.segundoproyecto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unaempresa.segundoproyecto.entity.Articulo;
import com.unaempresa.segundoproyecto.exception.ArticuloNoEncontradoException;
import com.unaempresa.segundoproyecto.repository.IArticuloRepository;

@Service
public class ArticuloServiceImpl implements IArticuloService {

    private final IArticuloRepository articuloRepository;

    public ArticuloServiceImpl(IArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Override
    public List<Articulo> findAll() {
        return articuloRepository.findAll();
    }

    @Override
    public Articulo findById(Long id) {
        return articuloRepository.findById(id)
                .orElseThrow(() -> new ArticuloNoEncontradoException(id));
    }

    @Override
    public void deleteById(Long id) {
        articuloRepository.deleteById(id);
    }

    @Override
    public Articulo save(Articulo articulo) {
        return articuloRepository.save(articulo);
    }
}

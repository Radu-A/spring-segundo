package com.unaempresa.segundoproyecto.service;

import java.util.List;

import com.unaempresa.segundoproyecto.entity.Articulo;

public interface IArticuloService {
    List<Articulo> findAll();
    Articulo findById(Long id);
    void deleteById(Long id);
    Articulo save(Articulo articulo);
}

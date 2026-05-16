package com.unaempresa.segundoproyecto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unaempresa.segundoproyecto.entity.Cliente;

public interface IClienteService {
    List<Cliente> findAll();
    Page<Cliente> findAllPaginado(Pageable pageable);
    Cliente findById(Long id);
    void deleteById(Long id);
    Cliente save(Cliente cliente);
    List<Cliente> findByNombre(String nombre);
}
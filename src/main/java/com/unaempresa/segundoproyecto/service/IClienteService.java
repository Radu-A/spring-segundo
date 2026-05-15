package com.unaempresa.segundoproyecto.service;

import java.util.List;

import com.unaempresa.segundoproyecto.entity.Cliente;

public interface IClienteService {
    List<Cliente> findAll();
    Cliente findById(Long id);
    void deleteById(Long id);
    Cliente save(Cliente cliente);
    List<Cliente> findByNombre(String nombre);
}
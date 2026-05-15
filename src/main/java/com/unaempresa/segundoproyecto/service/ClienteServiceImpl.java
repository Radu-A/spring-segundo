package com.unaempresa.segundoproyecto.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unaempresa.segundoproyecto.entity.Cliente;
import com.unaempresa.segundoproyecto.exception.ClienteNoEncontradoException;
import com.unaempresa.segundoproyecto.repository.IClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteServiceImpl(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    // En lugar de devolver null cuando el id no existe,
    // lanzamos una excepción de negocio que el @ControllerAdvice capturará.
    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException(id));
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findByNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
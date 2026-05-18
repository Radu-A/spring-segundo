package com.unaempresa.segundoproyecto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAllPaginado(Pageable pageable) {
        // JpaRepository ya implementa findAll(Pageable) — solo delegamos.
        return clienteRepository.findAll(pageable);
    }

    // En lugar de devolver null cuando el id no existe,
    // lanzamos una excepción de negocio que el @ControllerAdvice capturará.
    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findByNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
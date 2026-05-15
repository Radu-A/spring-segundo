package com.unaempresa.segundoproyecto.exception;

public class ArticuloNoEncontradoException extends RuntimeException {

    public ArticuloNoEncontradoException(Long id) {
        super("No se encontró ningún artículo con id: " + id);
    }
}

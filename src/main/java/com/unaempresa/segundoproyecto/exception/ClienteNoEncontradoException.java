package com.unaempresa.segundoproyecto.exception;

// Excepción de negocio: se lanza cuando se busca un cliente por id y no existe.
// Extiende RuntimeException (no comprobada): no hay que declararla en las firmas de métodos.
public class ClienteNoEncontradoException extends RuntimeException {

    public ClienteNoEncontradoException(Long id) {
        super("No se encontró ningún cliente con id: " + id);
    }

}
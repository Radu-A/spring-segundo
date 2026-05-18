package com.unaempresa.segundoproyecto.exception;

// Excepción de negocio: se lanza cuando se busca un cliente por id y no existe.
// Extiende RuntimeException (no comprobada): no hay que declararla en las firmas de métodos.
public class PedidoNoEncontradoException extends RuntimeException {

    public PedidoNoEncontradoException(Long id) {
        super("No se encontró ningún pedido con id: " + id);
    }

}
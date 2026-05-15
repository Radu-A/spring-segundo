package com.unaempresa.segundoproyecto.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice: intercepta excepciones lanzadas desde cualquier @Controller.
// Es el "net" global que recoge lo que los controladores no manejan ellos mismos.
@ControllerAdvice
public class GlobalExceptionHandler {

    // Captura ClienteNoEncontradoException venga del controlador que venga.
    @ExceptionHandler(ClienteNoEncontradoException.class)
    public String handleClienteNoEncontrado(ClienteNoEncontradoException ex, Model model) {
        model.addAttribute("titulo", "Cliente no encontrado");
        model.addAttribute("mensaje", ex.getMessage());
        return "error/cliente-no-encontrado";
    }

    @ExceptionHandler(ArticuloNoEncontradoException.class)
    public String handleArticuloNoEncontrado(ArticuloNoEncontradoException ex, Model model) {
        model.addAttribute("titulo", "Artículo no encontrado");
        model.addAttribute("mensaje", ex.getMessage());
        return "error/articulo-no-encontrado";
    }

}
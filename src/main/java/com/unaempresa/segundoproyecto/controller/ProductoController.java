package com.unaempresa.segundoproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unaempresa.segundoproyecto.service.IProductoService;

@Controller
@RequestMapping("productos")
public class ProductoController {
	
    private final IProductoService productoService;

    @ModelAttribute(name = "titulo")
    public String titulo() {
        return "Productos";
    }

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }
    
    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String nombre, Model model) {
        model.addAttribute("cabecera", "Buscar productos por nombre");
        if (nombre != null && !nombre.isEmpty()) {
            model.addAttribute("productos", productoService.findByNombre(nombre));
        }
        return "producto/buscar";
    }
}

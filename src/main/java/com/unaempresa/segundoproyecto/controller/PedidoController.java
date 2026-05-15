package com.unaempresa.segundoproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unaempresa.segundoproyecto.service.IPedidoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final IPedidoService pedidoService;

    @ModelAttribute(name = "titulo")
    public String titulo() {
        return "Pedidos";
    }

    public PedidoController(IPedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/por-estado")
    public String porEstado(@RequestParam(required = false) String estado, Model model) {
        model.addAttribute("cabecera", "Buscar pedidos por estado");
        if (estado != null && !estado.isEmpty()) {
            model.addAttribute("pedidos", pedidoService.findByEstado(estado));
            model.addAttribute("estadoSeleccionado", estado);
        }
        return "pedido/por-estado";
    }

    @GetMapping("/por-cliente/{id}")
    public String porCliente(@PathVariable Long id, Model model) {
        model.addAttribute("cabecera", "Pedidos del cliente");
        model.addAttribute("pedidos", pedidoService.findByClienteId(id));
        return "pedido/por-cliente";
    }
}

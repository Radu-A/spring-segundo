package com.unaempresa.segundoproyecto.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unaempresa.segundoproyecto.service.IClienteService;
import com.unaempresa.segundoproyecto.service.IPedidoService;
import com.unaempresa.segundoproyecto.service.IProductoService;

@Controller
@RequestMapping("/pedidos")
@Transactional
public class PedidoController {

    private final IPedidoService pedidoService;
    private final IClienteService clienteService;
    private final IProductoService productoService;

    public PedidoController(IPedidoService pedidoService, IClienteService clienteService,
			IProductoService productoService) {
		this.pedidoService = pedidoService;
		this.clienteService = clienteService;
		this.productoService = productoService;
	}

	@ModelAttribute(name = "titulo")
    public String titulo() {
        return "Pedidos";
    }

    @GetMapping("/por-estado")
    @Transactional(readOnly = true)
    public String porEstado(@RequestParam(required = false) String estado, Model model) {
        model.addAttribute("cabecera", "Buscar pedidos por estado");
        if (estado != null && !estado.isEmpty()) {
            model.addAttribute("pedidos", pedidoService.findByEstado(estado));
            model.addAttribute("estadoSeleccionado", estado);
        }
        return "pedido/por-estado";
    }

    @GetMapping("/por-cliente/{id}")
    @Transactional(readOnly = true)
    public String porCliente(@PathVariable Long id, Model model) {
        model.addAttribute("cabecera", "Pedidos del cliente");
        model.addAttribute("pedidos", pedidoService.findByClienteId(id));
        return "pedido/por-cliente";
    }
    
    @GetMapping("/lista")
    @Transactional(readOnly = true)
    public String lista(Model model) {
        model.addAttribute("cabecera", "Listado de todos los pedidos");
    	model.addAttribute("pedidos", pedidoService.findAll());
    	return "pedido/listado";
    }
    
    @GetMapping("/id/{id}")
    @Transactional(readOnly = true)
    public String lista(Model model, @PathVariable Long id) {
        model.addAttribute("cabecera", "Detalle del pedido");
    	model.addAttribute("pedido", pedidoService.findById(id));
    	return "pedido/un-pedido";
    }
    
    @GetMapping("/borrar/{id}")
    public String borrar(Model model, @PathVariable Long id) {
    	pedidoService.deleteById(id);
    	return "redirect:/pedidos/lista";
    }
    
    @GetMapping("/nuevo")
    @Transactional(readOnly = true)
    public String nuevo(Model model) {
        model.addAttribute("cabecera", "Nuevo pedido");
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("productos", productoService.findAll());
    	return "pedido/form";
    }
    
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long clienteId,
    		@RequestParam(required = false) List<Long> productoIds,
    		@RequestParam String estado,
    		@RequestParam BigDecimal total) {
    	if(productoIds == null) productoIds = new ArrayList<>();
    	pedidoService.crearPedidoConProductos(clienteId, productoIds, estado, total);
    	return "redirect:/pedidos/lista";
    }
    
}

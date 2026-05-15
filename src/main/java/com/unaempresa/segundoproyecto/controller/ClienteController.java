package com.unaempresa.segundoproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unaempresa.segundoproyecto.entity.Cliente;
import com.unaempresa.segundoproyecto.service.IClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    @ModelAttribute(name = "titulo")
    public String titulo() {
        return "Clientes";
    }

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("cabecera", "Listado de todos los clientes");
        model.addAttribute("intro", "Esta es la lista de todos los clientes:");
        model.addAttribute("clientes", clienteService.findAll());
        return "cliente/listado";
    }
    
    @GetMapping("/id/{id}")
    public String elementoPorId(Model model, @PathVariable Long id) {
        model.addAttribute("cabecera", "Información de cliente");
        model.addAttribute("cliente", clienteService.findById(id));
        return "cliente/un-cliente";
    }
    
    @GetMapping("/borrar/{id}")
    public String borrarPorId(@PathVariable Long id) {
        clienteService.deleteById(id);
        return "redirect:/clientes/lista";
    }
    
 // Paso 1: mostrar el formulario con los datos del cliente
    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Long id) {
        model.addAttribute("cabecera", "Editar cliente");
        model.addAttribute("cliente", clienteService.findById(id));
        return "cliente/form";
    }

    // UPDATE - Recibir formulario y guardar cambios
    @PostMapping("/actualizar")
    public String actualizar(@Valid Cliente cliente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cabecera", "Editar cliente");
            return "cliente/form";
        }
        clienteService.save(cliente);
        return "redirect:/clientes/lista";
    }
    
 // Mostrar formulario vacío para un nuevo cliente
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cabecera", "Nuevo cliente");
        model.addAttribute("cliente", new Cliente());
        return "cliente/form";
    }

    // @Valid activa Bean Validation sobre el objeto Cliente.
    // BindingResult recoge los errores sin lanzar excepción.
    // IMPORTANTE: BindingResult debe ser el parámetro inmediatamente siguiente a @Valid.

    @PostMapping("/guardar")
    public String guardar(@Valid Cliente cliente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cabecera", "Nuevo cliente");
            return "cliente/form";
        }
        clienteService.save(cliente);
        return "redirect:/clientes/lista";
    }

    
}
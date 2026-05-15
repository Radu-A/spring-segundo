package com.unaempresa.segundoproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unaempresa.segundoproyecto.entity.Articulo;
import com.unaempresa.segundoproyecto.service.IArticuloService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {

    private final IArticuloService articuloService;

    @ModelAttribute(name = "titulo")
    public String titulo() {
        return "Artículos";
    }

    public ArticuloController(IArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("cabecera", "Listado de todos los artículos");
        model.addAttribute("intro", "Esta es la lista de todos los artículos:");
        model.addAttribute("articulos", articuloService.findAll());
        return "articulo/listado";
    }

    @GetMapping("/id/{id}")
    public String elementoPorId(Model model, @PathVariable Long id) {
        model.addAttribute("cabecera", "Información de artículo");
        model.addAttribute("articulo", articuloService.findById(id));
        return "articulo/un-articulo";
    }

    @GetMapping("/borrar/{id}")
    public String borrarPorId(@PathVariable Long id) {
        articuloService.deleteById(id);
        return "redirect:/articulos/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Long id) {
        model.addAttribute("cabecera", "Editar artículo");
        model.addAttribute("articulo", articuloService.findById(id));
        return "articulo/form";
    }

    @PostMapping("/actualizar")
    public String actualizar(@Valid Articulo articulo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cabecera", "Editar artículo");
            return "articulo/form";
        }
        articuloService.save(articulo);
        return "redirect:/articulos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cabecera", "Nuevo artículo");
        model.addAttribute("articulo", new Articulo());
        return "articulo/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Articulo articulo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cabecera", "Nuevo artículo");
            return "articulo/form";
        }
        articuloService.save(articulo);
        return "redirect:/articulos/lista";
    }
}

package com.unaempresa.segundoproyecto.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unaempresa.segundoproyecto.entity.Categoria;
import com.unaempresa.segundoproyecto.service.ICategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
	
	private ICategoriaService categoriaService;

	public CategoriaController(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@ModelAttribute("titulo")
	public String titulo() {
		return "Categorías";
	}
	
	@GetMapping("/lista")
	public String lista(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			Model model) {
		Page<Categoria> pagina = categoriaService.findAllPaginado(PageRequest.of(page, size));
        model.addAttribute("cabecera", "Listado de todas las categorías");
		model.addAttribute("categorias", pagina);
		return "categoria/listado";
	}
	
	@GetMapping("/id/{id}")
	public String detalles(@PathVariable Long id, Model model) {
        model.addAttribute("cabecera", "Detalles de la categoria");
        model.addAttribute("categoria", categoriaService.findById(id));
        return "categoria/una-categoria";
	}
}

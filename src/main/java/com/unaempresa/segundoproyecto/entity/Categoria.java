package com.unaempresa.segundoproyecto.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "{categoria.nombre.notblank}")
	@Size(min = 2, max = 50, message = "{categoria.nombre.size}")
	private String nombre;
	@NotBlank(message = "{categoria.descripcion.notblank}")
	@Size(max = 200, message = "{categoria.descripcion.size}")
	private String descripcion;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "categoria_productos",
			joinColumns = @JoinColumn(name="categoria_id"),
			inverseJoinColumns = @JoinColumn(name="producto_id")
		)
	List<Producto> productos = new ArrayList<>();
}

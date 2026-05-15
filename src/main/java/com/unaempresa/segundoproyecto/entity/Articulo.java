package com.unaempresa.segundoproyecto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "articulos")
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{articulo.nombre.notblank}")
    @Size(min = 2, max = 100, message = "{articulo.nombre.size}")
    private String nombre;

    @Size(max = 255, message = "{articulo.descripcion.size}")
    private String descripcion;

    @NotNull(message = "{articulo.precio.notnull}")
    @DecimalMin(value = "0", inclusive = false, message = "{articulo.precio.decimalmin}")
    private Double precio;

    @NotNull(message = "{articulo.stock.notnull}")
    @Min(value = 0, message = "{articulo.stock.min}")
    private Integer stock;

    // Constructor sin argumentos — obligatorio para JPA
    public Articulo() {
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}

package com.unaempresa.segundoproyecto.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "productos")
public class Producto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{producto.nombre.notblank}")
    @Size(min = 2, max = 100, message = "{producto.nombre.size}")
    private String nombre;

    @NotNull(message = "{producto.precio.notnull}")
    @DecimalMin(value = "0.0", message = "{producto.precio.min}")
    private BigDecimal precio;
    
    // Lado inverso del @ManyToMany: mappedBy apunta al campo 'productos' en Pedido.
    // Hibernate NO gestiona esta lista para persistir — solo sirve para navegar.
    @ManyToMany(mappedBy = "productos", fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();
    
    public Producto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}

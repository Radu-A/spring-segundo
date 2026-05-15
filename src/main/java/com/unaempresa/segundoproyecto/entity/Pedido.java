package com.unaempresa.segundoproyecto.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{pedido.fecha.notnull}")
    private LocalDate fecha;

    @NotBlank(message = "{pedido.estado.notblank}")
    private String estado; // PENDIENTE | COMPLETADO | CANCELADO

    @NotNull(message = "{pedido.total.notnull}")
    @DecimalMin(value = "0.0", message = "{pedido.total.min}")
    private BigDecimal total;
    
    // Lado propietario de la relación: Pedido tiene la FK cliente_id.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    	name = "pedido_productos",
    	joinColumns = @JoinColumn(name="pedido_id"),
    	inverseJoinColumns = @JoinColumn(name="producto_id")
    )   
    private List<Producto> productos = new ArrayList<>();
    
    public Pedido() {}

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
}

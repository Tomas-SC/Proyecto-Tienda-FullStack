package com.tomassc.tiendaapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Le dice al JPA que esta clase representa una tabla en la base de datos
public class Producto {

    @Id // Marca este campo como la clave primaria (ID único) de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Le dice a MySQL que genere el ID automáticamente al insertar
    private Long id;

    private String nombre;
    private String descripcion;
    private String categoria;
    private String imagenUrl; 
    private double precio;
    private int stock;

    // Constructores 
    // Un constructor vacío es requerido por JPA
    public Producto() {
    }

    // Constructor con todos los campos para facilitar la creación de objetos
    public Producto(String nombre, String descripcion, String categoria, String imagenUrl, double precio, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imagenUrl = imagenUrl;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
  
    public Long getId() {
        return id;
    }
     

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getImagenUrl() {
        return imagenUrl;
    }
    
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
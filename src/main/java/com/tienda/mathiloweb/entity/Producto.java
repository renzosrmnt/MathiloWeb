package com.tienda.mathiloweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@NotBlank(message = "Ingrese el nombre")
	@Size(min=1, max=50)
    @Column(name = "nombre")
    private String nombre;
	

	@Size(min=0, max=100)
    @Column(name = "descripcion")
    private String descripcion;
	

    @Column(name = "peso")
    private double peso;
	

    @Column(name = "altura")
    private double altura;
	

    @Column(name = "ancho")
    private double ancho;


    @Column(name = "largo")
    private double largo;

    @Size(min=0, max=100)
    @Column(name = "material")
    private String material;
	

	@Size(min=1, max=100)
    @Column(name = "color")
    private String color;
	

	@NotNull(message = "Ingrese el precio")
    @Column(name = "precio")
    private double precio;
	
	@Size(min=1, max=255)
    @Column(name = "image")
    private String image;
	

	@NotNull(message = "Ingrese el stock")
    @Column(name = "stock")
    private int stock;
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
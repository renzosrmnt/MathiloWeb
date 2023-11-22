package com.tienda.mathiloweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venta_categoria")
public class VentaCategoria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@NotBlank
	@Size(min=1, max=50)
    @Column(name = "nombre", unique = true)
    private String nombre;
	
	@Size(min=0, max=100)
    @Column(name = "descripcion")
    private String descripcion;

}

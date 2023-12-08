package com.tienda.mathiloweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paypal_pago")
public class PaypalPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "contraseña", length = 250)
    private String contraseña;

    @Column(name = "total_carrito", precision = 10, scale = 2)
    private BigDecimal totalCarrito;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;
}

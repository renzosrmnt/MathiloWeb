package com.tienda.mathiloweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tienda.mathiloweb.entity.VentaProducto;

@Repository
public interface IVentaProductoRepository extends JpaRepository <VentaProducto, Long> {

}

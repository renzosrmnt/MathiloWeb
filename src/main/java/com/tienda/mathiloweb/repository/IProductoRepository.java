package com.tienda.mathiloweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tienda.mathiloweb.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository <Producto, Long> {

}

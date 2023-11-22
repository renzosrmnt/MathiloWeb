package com.tienda.mathiloweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tienda.mathiloweb.entity.VentaCategoria;

@Repository
public interface IVentaCategoriaRepository extends JpaRepository<VentaCategoria, Long> {
	
}

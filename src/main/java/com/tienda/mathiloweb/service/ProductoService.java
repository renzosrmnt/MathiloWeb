package com.tienda.mathiloweb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tienda.mathiloweb.entity.Producto;

public interface ProductoService {
	
	public List<Producto> findAll();

	public Page<Producto> findAll(Pageable pageable);

	public void save(Producto producto);

	public Producto findById(Long id);

	public void delete(Long id);
	
}

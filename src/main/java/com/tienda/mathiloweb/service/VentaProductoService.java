package com.tienda.mathiloweb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tienda.mathiloweb.entity.VentaProducto;

public interface VentaProductoService {
	
	public List<VentaProducto> findAll();

	public Page<VentaProducto> findAll(Pageable pageable);

	public void save(VentaProducto producto);

	public VentaProducto findById(Long id);

	public void delete(Long id);
	
}

package com.tienda.mathiloweb.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tienda.mathiloweb.entity.VentaCategoria;

public interface VentaCategoriaService {
	
	public List<VentaCategoria> findAll();

	public Page<VentaCategoria> findAll(Pageable pageable);

	public void save(VentaCategoria categoria);

	public VentaCategoria findById(Long id);
	

	public void delete(Long id);

}


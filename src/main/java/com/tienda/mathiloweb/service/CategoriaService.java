package com.tienda.mathiloweb.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tienda.mathiloweb.entity.Categoria;

public interface CategoriaService {
	
	public List<Categoria> findAll();

	public Page<Categoria> findAll(Pageable pageable);

	public void save(Categoria categoria);

	public Categoria findById(Long id);
	

	public void delete(Long id);

}


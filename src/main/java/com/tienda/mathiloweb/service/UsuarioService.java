package com.tienda.mathiloweb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.tienda.mathiloweb.entity.Usuario;

public interface UsuarioService {
	
	public List<Usuario> findAll();

	public Page<Usuario> findAll(Pageable pageable);

	public void save(Usuario usuario);

	public Usuario findById(Long id);
	
	public void delete(Long id);

}

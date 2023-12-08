package com.tienda.mathiloweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tienda.mathiloweb.entity.Usuario;
import com.tienda.mathiloweb.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		Optional<Usuario> optionalUsuario= usuarioRepository.findById(id);
	    return optionalUsuario.orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioRepository.deleteById(id);	
	}

}

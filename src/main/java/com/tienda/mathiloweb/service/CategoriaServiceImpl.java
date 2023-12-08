package com.tienda.mathiloweb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.mathiloweb.entity.Categoria;
import com.tienda.mathiloweb.repository.ICategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Categoria> findAll(Pageable pageable) {
		return categoriaRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Categoria categoria) {
		categoriaRepository.save(categoria);
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findById(Long id) {
	    Optional<Categoria> optionalVentaCategoria = categoriaRepository.findById(id);
	    return optionalVentaCategoria.orElse(null);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		categoriaRepository.deleteById(id);
	}


}



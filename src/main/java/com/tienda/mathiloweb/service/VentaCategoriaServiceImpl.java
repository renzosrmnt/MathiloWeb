package com.tienda.mathiloweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tienda.mathiloweb.entity.VentaCategoria;
import com.tienda.mathiloweb.repository.IVentaCategoriaRepository;

@Service
public class VentaCategoriaServiceImpl implements VentaCategoriaService {
	
	@Autowired
	private IVentaCategoriaRepository categoriaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<VentaCategoria> findAll() {
		return categoriaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<VentaCategoria> findAll(Pageable pageable) {
		return categoriaRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(VentaCategoria categoria) {
		categoriaRepository.save(categoria);
		
	}

	@Override
	@Transactional(readOnly = true)
	public VentaCategoria findById(Long id) {
	    Optional<VentaCategoria> optionalVentaCategoria = categoriaRepository.findById(id);
	    return optionalVentaCategoria.orElse(null);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		categoriaRepository.deleteById(id);
		
	}


}



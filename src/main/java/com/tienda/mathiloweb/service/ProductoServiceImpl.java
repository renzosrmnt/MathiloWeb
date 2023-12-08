package com.tienda.mathiloweb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tienda.mathiloweb.entity.Producto;
import com.tienda.mathiloweb.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Producto> findAll(Pageable pageable) {
		return productoRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Producto producto) {
		productoRepository.save(producto);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
	    Optional<Producto> optionalVentaProducto = productoRepository.findById(id);
	    return optionalVentaProducto.orElse(null);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		productoRepository.deleteById(id);
		
	}
	
}

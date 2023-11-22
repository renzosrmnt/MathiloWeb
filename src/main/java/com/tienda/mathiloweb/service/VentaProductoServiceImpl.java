package com.tienda.mathiloweb.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tienda.mathiloweb.entity.VentaProducto;
import com.tienda.mathiloweb.repository.IVentaProductoRepository;

@Service
public class VentaProductoServiceImpl implements VentaProductoService {
	
	@Autowired
	private IVentaProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<VentaProducto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<VentaProducto> findAll(Pageable pageable) {
		return productoRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(VentaProducto producto) {
		productoRepository.save(producto);
		
	}

	@Override
	@Transactional(readOnly = true)
	public VentaProducto findById(Long id) {
	    Optional<VentaProducto> optionalVentaProducto = productoRepository.findById(id);
	    return optionalVentaProducto.orElse(null);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		productoRepository.deleteById(id);
		
	}
	
}

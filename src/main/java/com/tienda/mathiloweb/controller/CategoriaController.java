package com.tienda.mathiloweb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tienda.mathiloweb.entity.Categoria;
import com.tienda.mathiloweb.service.CategoriaService;
import com.tienda.mathiloweb.util.pagination.PageRender;

import jakarta.validation.Valid;

@Controller
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("/categoria/{id}")
	public String verDetallesCategoria(@PathVariable(value = "id") Long id,Map<String,Object> modelo,RedirectAttributes flash) {
		Categoria categoria = categoriaService.findById(id);
		if(categoria == null) {
			flash.addFlashAttribute("error", "La categoría no existe en la base de datos");
			return "redirect:/categorias";
		}
		
		modelo.put("categoria",categoria);
		modelo.put("titulo", "Detalles de la categoria " + categoria.getNombre());
		return "detallesCategoria";
	}
	
	@GetMapping("/categoria")
	public String listarCategorias(@RequestParam(name = "page",defaultValue = "0") int page,Model modelo) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Categoria> categorias = categoriaService.findAll(pageRequest);
		PageRender<Categoria> pageRender = new PageRender<>("/categoria", categorias);
		
		modelo.addAttribute("titulo","Listado de categorias");
		modelo.addAttribute("categorias",categorias);
		modelo.addAttribute("page", pageRender);
		
		return "listarCategorias";
	}
	
	@GetMapping("/categoria/nueva-categoria")
	public String mostrarFormularioCategoria(Model modelo) {
	    modelo.addAttribute("categoria", new Categoria());
	    modelo.addAttribute("titulo", "Registro de categoría");
	    return "formularioCategoria";
	}

	@PostMapping("/categoria/guardar-categoria")
	public String guardarCategoria(@Valid Categoria categoria, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status) {
	    if (result.hasErrors()) {
	        modelo.addAttribute("titulo", "Registro de categoría");
	        return "formularioCategoria";
	    }

	    try {
	        categoriaService.save(categoria);
	        status.setComplete();
	        flash.addFlashAttribute("success", "¡Categoría registrada con éxito!");
	        return "redirect:/categoria";
	    } catch (DataIntegrityViolationException e) {
	        flash.addFlashAttribute("error", "El nombre de la categoría ya existe");
	        return "redirect:/categoria/nueva-categoria";
	    }
	}

    @GetMapping("/categoria/editar-categoria/{id}")
    public String editarCategoria(@PathVariable(value = "id") Long id, Model modelo, RedirectAttributes flash) {
        if (id != null && id > 0) {
            Categoria categoria = categoriaService.findById(id);
            if (categoria == null) {
                flash.addFlashAttribute("error", "La categoría no existe en la base de datos");
                return "redirect:/categoria";
            }
            modelo.addAttribute("categoria", categoria);
        } else {
            flash.addFlashAttribute("error", "El ID de la categoría no puede ser nulo o cero");
            return "redirect:/categoria";
        }

        modelo.addAttribute("titulo", "Edición de categoría");
        return "formularioCategoria";
    }
	
	@GetMapping("/categoria/eliminar-categoria/{id}")
	public String eliminarCategoria(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		if(id > 0) {
			categoriaService.delete(id);
			flash.addFlashAttribute("success", "Categoria eliminada con exito");
		}
		return "redirect:/categoria";
	}
	
}
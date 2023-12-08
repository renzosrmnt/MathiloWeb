package com.tienda.mathiloweb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tienda.mathiloweb.entity.Usuario;
import com.tienda.mathiloweb.service.UsuarioService;
import com.tienda.mathiloweb.util.pagination.PageRender;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	
	@Autowired
    private UsuarioService usuarioService;
	
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
    

    @GetMapping("/usuario/{id}")
    public String verDetallesUsuario(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El producto no existe en la base de datos");
            return "redirect:/productos";
        }
   
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Detalles del producto " + usuario.getNombre());
        
        return "detallesUsuario";
    }

    @GetMapping("/usuario")
    public String listarUsuarios(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<Usuario> usuarios = usuarioService.findAll(pageRequest);
        PageRender<Usuario> pageRender = new PageRender<>("/usuario", usuarios);

        model.addAttribute("titulo", "Listado de productos");
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("page", pageRender);

        return "listarUsuarios";
    }

    @GetMapping("/nuevo-usuario")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("titulo", "Registro de producto");
        
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        
        return "formularioUsuario";
    }

    @PostMapping("/guardar-usuario")
    public String guardarUsuario(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de usuario");
            return "formularioUsuario";
        }

        try {
	        usuarioService.save(usuario);
	        status.setComplete();
	        flash.addFlashAttribute("success", "¡Usuario registrado con éxito!");
	        return "redirect:/usuario";
	    } catch (DataIntegrityViolationException e) {
	        flash.addFlashAttribute("error", "El nombre de la categoría ya existe");
	        return "redirect:/nuevo-usuario";
	    }
    }

    @GetMapping("/editar-usuario/{id}")
    public String editarUsuario(@PathVariable(value = "id") Long id, Model modelo, RedirectAttributes flash) {
        if (id != null && id > 0) {
            Usuario usuario = usuarioService.findById(id);
            if (usuario == null) {
                flash.addFlashAttribute("error", "El usuario no existe en la base de datos");
                return "redirect:/productos";
            }
            modelo.addAttribute("usuario", usuario);
        } else {
            flash.addFlashAttribute("error", "El ID del usuario no puede ser nulo o cero");
            return "redirect:/usuario";
        }
        modelo.addAttribute("titulo", "Edición de categoría");
        return "formularioUsuario";
    }

    @GetMapping("/eliminar-usuario/{id}")
    public String eliminarUsuario(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            usuarioService.delete(id);
            flash.addFlashAttribute("success", "Usuario eliminado con éxito");
        }
        return "redirect:/usuario";
    }
	
	
}

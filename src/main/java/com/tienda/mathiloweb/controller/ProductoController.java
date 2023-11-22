package com.tienda.mathiloweb.controller;

import com.tienda.mathiloweb.entity.VentaCategoria;
import com.tienda.mathiloweb.entity.VentaProducto;
import com.tienda.mathiloweb.service.VentaCategoriaService;
import com.tienda.mathiloweb.service.VentaProductoService;
import com.tienda.mathiloweb.util.pagination.PageRender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class ProductoController {

    @Autowired
    private VentaProductoService productoService;
    @Autowired
    private VentaCategoriaService categoriaService;
    

    @GetMapping("/producto/{id}")
    public String verDetallesProducto(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        VentaProducto producto = productoService.findById(id);
        if (producto == null) {
            flash.addFlashAttribute("error", "El producto no existe en la base de datos");
            return "redirect:/productos";
        }
        
        List<VentaCategoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Detalles del producto " + producto.getNombre());
        
        return "detallesProducto";
    }

    @GetMapping({"/", "/productos", ""})
    public String listarProductos(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 5);
        Page<VentaProducto> productos = productoService.findAll(pageRequest);
        PageRender<VentaProducto> pageRender = new PageRender<>("/productos", productos);

        model.addAttribute("titulo", "Listado de productos");
        model.addAttribute("productos", productos);
        model.addAttribute("page", pageRender);

        return "listarProductos";
    }

    @GetMapping("/nuevo-producto")
    public String mostrarFormularioProducto(Model model) {
        model.addAttribute("producto", new VentaProducto());
        model.addAttribute("titulo", "Registro de producto");
        
        List<VentaCategoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        
        return "formularioProducto";
    }

    @PostMapping("/registro-producto")
    public String guardarProducto(@Valid VentaProducto producto, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status, @RequestParam("file") MultipartFile file) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de producto");
            return "formularioProducto";
        }

        try {
        	if (!file.isEmpty()) {
                String rutaBase = "/images/";
                String nombreArchivo = file.getOriginalFilename();
                String rutaCompleta = System.getProperty("user.dir") + "/src/main/resources/static/images/" + nombreArchivo;

                Path rutaImagen = Paths.get(rutaCompleta);
                Files.write(rutaImagen, file.getBytes());

                producto.setImage(rutaBase + nombreArchivo);
            }
            productoService.save(producto);
            status.setComplete();
            flash.addFlashAttribute("success", "¡Producto registrado con éxito!");
            return "redirect:/productos";
        } catch (DataIntegrityViolationException e) {
            flash.addFlashAttribute("error", "Error al procesar la imagen o guardar el producto");
            return "redirect:/nuevo-producto";
        }
    }

    @GetMapping("/editar-producto/{id}")
    public String editarProducto(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
        if (id != null && id > 0) {
            VentaProducto producto = productoService.findById(id);
            if (producto == null) {
                flash.addFlashAttribute("error", "El producto no existe en la base de datos");
                return "redirect:/productos";
            }
            model.addAttribute("producto", producto);
        } else {
            flash.addFlashAttribute("error", "El ID del producto no puede ser nulo o cero");
            return "redirect:/productos";
        }
        
        List<VentaCategoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        model.addAttribute("titulo", "Edición de producto");
        return "formularioProducto";
    }

    @GetMapping("/eliminar-producto/{id}")
    public String eliminarProducto(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            productoService.delete(id);
            flash.addFlashAttribute("success", "Producto eliminado con éxito");
        }
        return "redirect:/productos";
    }
}

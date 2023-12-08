package com.tienda.mathiloweb.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CategoriaExceptionHandle {
	@ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            redirectAttributes.addFlashAttribute("error", "Completa este campo");
        }
        return "redirect:/categorias";
    }
}

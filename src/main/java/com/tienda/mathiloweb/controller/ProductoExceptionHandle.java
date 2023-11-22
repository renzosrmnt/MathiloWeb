package com.tienda.mathiloweb.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ProductoExceptionHandle {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleProductoDataIntegrityViolation(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            redirectAttributes.addFlashAttribute("error", "El valor ingresado ya existe para el producto, por favor elige otro.");
        }
        return "redirect:/productos";
    }
}

package com.capitole.testProject.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String applicationDate, int brandId, int productId) {
        super("No se encontro una lista de precios perteneciente a la fecha: "
                + applicationDate + " para el pruducto ID: " + productId + " y cadena ID: " + brandId);
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

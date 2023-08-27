package com.capitole.testProject.core.infrastructure.exceptions;

import org.webjars.NotFoundException;

import java.util.Date;

public class PriceNotFoundException extends NotFoundException {

    public PriceNotFoundException(Date applicationDate, int brandId, int productId) {
        super("No se encontro una lista de precios perteneciente a la fecha: "
                + applicationDate + " para el pruducto ID: " + productId + " y cadena ID: " + brandId);
    }
}

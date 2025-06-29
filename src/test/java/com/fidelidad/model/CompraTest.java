package com.fidelidad.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompraTest {

    @Test
    void constructorAndGetters() {
        String idCompra = "c1";
        String idCliente = "u1";
        double monto = 123.45;
        LocalDate fecha = LocalDate.of(2025, 6, 28);

        Compra compra = new Compra(idCompra, idCliente, monto, fecha);

        assertEquals(idCompra, compra.getIdCompra());
        assertEquals(idCliente, compra.getClienteId());
        assertEquals(monto, compra.getMonto());
        assertEquals(fecha, compra.getFecha());
    }

    @Test
    void toStringIncludesFields() {
        Compra compra = new Compra("c2", "u2", 200.0, LocalDate.of(2025, 1, 1));
        String text = compra.toString();
        assertTrue(text.contains("ID: c2"));
        assertTrue(text.contains("Cliente: u2"));
        assertTrue(text.contains("Monto: 200,00"));
        assertTrue(text.contains("Fecha: 2025-01-01"));
    }
}

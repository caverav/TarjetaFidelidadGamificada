package com.fidelidad.service;

import com.fidelidad.model.Cliente;
import com.fidelidad.model.Compra;
import com.fidelidad.model.Nivel;
import com.fidelidad.repository.InMemoryClienteRepository;
import com.fidelidad.repository.InMemoryCompraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FidelidadServiceExtraTest {

    private FidelidadService svc;
    private Cliente cliente;
    private LocalDate dia1 = LocalDate.of(2025, 6, 28);
    private LocalDate dia2 = dia1.plusDays(1);

    @BeforeEach
    void setUp() {
        svc = new FidelidadService(
            new InMemoryClienteRepository(),
            new InMemoryCompraRepository()
        );
        cliente = new Cliente("1", "Test", "t@e.com");
        svc.agregarCliente(cliente);
    }

    @Test
    void bonoEnTerceraCompra() {
        for (int i = 1; i <= 3; i++) {
            svc.registrarCompra(new Compra("c" + i, "1", 100, dia1));
        }
        assertEquals(13, cliente.getPuntos());
    }

    @Test
    void streakResetNextDay() {
        svc.registrarCompra(new Compra("c1", "1", 100, dia1));
        svc.registrarCompra(new Compra("c2", "1", 100, dia1));
        svc.registrarCompra(new Compra("c3", "1", 100, dia2));
        assertEquals(3, cliente.getPuntos());
    }

    @Test
    void actualizarClienteNoExistenteLanza() {
        Cliente otro = new Cliente("x", "X", "x@e.com");
        assertThrows(IllegalArgumentException.class, () -> svc.actualizarCliente(otro));
    }

    @Test
    void eliminarClienteYListar() {
        svc.eliminarCliente("1");
        assertTrue(svc.listarClientes().isEmpty());
        assertThrows(NoSuchElementException.class, () -> svc.eliminarCliente("1"));
    }

    @Test
    void consultarPuntosYNivel() {
        svc.registrarCompra(new Compra("p1", "1", 500, dia1));
        Cliente c = svc.listarClientes().stream()
            .filter(cli -> cli.getId().equals("1"))
            .findFirst()
            .orElseThrow();
        assertEquals(5, c.getPuntos());
        assertEquals(Nivel.BRONCE, c.getNivel());
    }
}

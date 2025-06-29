package com.fidelidad.repository;

import com.fidelidad.model.Compra;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCompraRepositoryEdgeCasesTest {

    @Test
    void listarVacioDevuelveListaVacia() {
        InMemoryCompraRepository repo = new InMemoryCompraRepository();
        List<Compra> all = repo.listar();
        assertTrue(all.isEmpty());
    }

    @Test
    void buscarNoExistenteDevuelveEmpty() {
        InMemoryCompraRepository repo = new InMemoryCompraRepository();
        Optional<Compra> maybe = repo.buscar("no-existe");
        assertTrue(maybe.isEmpty());
    }
}


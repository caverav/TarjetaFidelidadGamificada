package com.fidelidad.repository;

import com.fidelidad.model.Cliente;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryClienteRepositoryEdgeCasesTest {

    @Test
    void listarVacioDevuelveListaVacia() {
        InMemoryClienteRepository repo = new InMemoryClienteRepository();
        List<Cliente> all = repo.listar();
        assertTrue(all.isEmpty());
    }

    @Test
    void buscarNoExistenteDevuelveEmpty() {
        InMemoryClienteRepository repo = new InMemoryClienteRepository();
        Optional<Cliente> maybe = repo.buscar("no-existe");
        assertTrue(maybe.isEmpty());
    }
}

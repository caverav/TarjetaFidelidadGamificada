package com.fidelidad.repository;

import com.fidelidad.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryClienteRepositoryTest {

    private InMemoryClienteRepository repo;
    private Cliente c1;

    @BeforeEach
    void setup() {
        repo = new InMemoryClienteRepository();
        c1 = new Cliente("id1", "Juan", "juan@example.com");
    }

    @Test
    void agregar_y_buscar_por_id() {
        repo.agregar(c1);
        Optional<Cliente> found = repo.buscar("id1");
        assertTrue(found.isPresent());
        assertEquals("Juan", found.get().getNombre());
    }

    @Test
    void listar_devuelve_todos_los_clientes() {
        Cliente c2 = new Cliente("id2", "Ana", "ana@example.com");
        repo.agregar(c1);
        repo.agregar(c2);
        List<Cliente> todos = repo.listar();
        assertEquals(2, todos.size());
        assertTrue(todos.contains(c1));
        assertTrue(todos.contains(c2));
    }

    @Test
    void agregar_cliente_duplicado_lanza_error() {
        repo.agregar(c1);
        Cliente dup = new Cliente("id1", "Otra", "otra@example.com");
        assertThrows(IllegalArgumentException.class, () -> repo.agregar(dup));
    }

    @Test
    void actualizar_cliente_existente() {
        repo.agregar(c1);
        c1.setNombre("Juanito");
        repo.actualizar(c1);
        assertEquals("Juanito", repo.buscar("id1").get().getNombre());
    }

    @Test
    void actualizar_cliente_no_existente_lanza_error() {
        assertThrows(NoSuchElementException.class, () -> repo.actualizar(c1));
    }

    @Test
    void eliminar_cliente() {
        repo.agregar(c1);
        repo.eliminar("id1");
        assertFalse(repo.buscar("id1").isPresent());
    }
}

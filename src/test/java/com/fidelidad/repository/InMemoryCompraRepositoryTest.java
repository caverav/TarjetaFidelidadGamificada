package com.fidelidad.repository;

import com.fidelidad.model.Compra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCompraRepositoryTest {

    private InMemoryCompraRepository repo;
    private Compra p1;

    @BeforeEach
    void setup() {
        repo = new InMemoryCompraRepository();
        p1 = new Compra("c1", "id1", 123.45, LocalDate.of(2025, 6, 28));
    }

    @Test
    void agregar_y_buscar_por_id() {
        repo.agregar(p1);
        Optional<Compra> found = repo.buscar("c1");
        assertTrue(found.isPresent());
        assertEquals(123.45, found.get().getMonto());
    }

    @Test
    void listar_devuelve_todas_las_compras() {
        Compra p2 = new Compra("c2", "id2", 50.0, LocalDate.now());
        repo.agregar(p1);
        repo.agregar(p2);
        List<Compra> todas = repo.listar();
        assertEquals(2, todas.size());
        assertTrue(todas.contains(p1));
        assertTrue(todas.contains(p2));
    }

    @Test
    void agregar_compra_duplicada_lanza_error() {
        repo.agregar(p1);
        Compra dup = new Compra("c1", "idX", 10.0, LocalDate.now());
        assertThrows(IllegalArgumentException.class, () -> repo.agregar(dup));
    }

    @Test
    void actualizar_compra_existente() {
        repo.agregar(p1);
        Compra mod = new Compra("c1", "id1", 999.99, p1.getFecha());
        repo.actualizar(mod);
        assertEquals(999.99, repo.buscar("c1").get().getMonto());
    }

    @Test
    void actualizar_compra_no_existente_lanza_error() {
        assertThrows(NoSuchElementException.class, () -> repo.actualizar(p1));
    }

    @Test
    void eliminar_compra() {
        repo.agregar(p1);
        repo.eliminar("c1");
        assertFalse(repo.buscar("c1").isPresent());
    }
}

package com.fidelidad.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void invalidEmailThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente("1","Juan","juan#example.com"));
    }

    @Test
    void agregarPuntosUpdatesLevel() {
        Cliente c = new Cliente("1","Ana","ana@e.com");
        assertEquals(Nivel.BRONCE, c.getNivel());
        c.agregarPuntos(500);
        assertEquals(Nivel.PLATA, c.getNivel());
        c.agregarPuntos(1000);
        assertEquals(Nivel.ORO, c.getNivel());
    }

    @Test
    void streakIncrementAndReset() {
        Cliente c = new Cliente("1","Pedro","p@e.com");
        assertEquals(0, c.getStreakDias());
        c.incrementStreak();
        c.incrementStreak();
        assertEquals(2, c.getStreakDias());
        c.resetStreak();
        assertEquals(0, c.getStreakDias());
    }

    @Test
    void equalsAndHashCodeById() {
        Cliente a = new Cliente("id","X","x@e.com");
        Cliente b = new Cliente("id","Y","y@e.com");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }
}

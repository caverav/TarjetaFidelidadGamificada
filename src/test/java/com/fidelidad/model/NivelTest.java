package com.fidelidad.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NivelTest {

    @Test
    void boundaryValues() {
        assertEquals(Nivel.BRONCE, Nivel.calcular(0));
        assertEquals(Nivel.BRONCE, Nivel.calcular(499));
        assertEquals(Nivel.PLATA,  Nivel.calcular(500));
        assertEquals(Nivel.PLATA,  Nivel.calcular(1499));
        assertEquals(Nivel.ORO,    Nivel.calcular(1500));
        assertEquals(Nivel.ORO,    Nivel.calcular(2999));
        assertEquals(Nivel.PLATINO,Nivel.calcular(3000));
        assertEquals(Nivel.PLATINO,Nivel.calcular(Integer.MAX_VALUE));
    }

    @Test
    void multiplierMatchesLevel() {
        assertEquals(1.0, Nivel.BRONCE.getMultiplicador());
        assertEquals(1.2, Nivel.PLATA.getMultiplicador());
        assertEquals(1.5, Nivel.ORO.getMultiplicador());
        assertEquals(2.0, Nivel.PLATINO.getMultiplicador());
    }

    @Test
    void belowZeroFallsToBronce() {
        assertEquals(Nivel.BRONCE, Nivel.calcular(-100));
    }
}


package com.fidelidad.model;

public enum Nivel {
    BRONCE(1.0, 0, 499),
    PLATA(1.2, 500, 1499),
    ORO(1.5, 1500, 2999),
    PLATINO(2.0, 3000, Integer.MAX_VALUE);

    private final double multiplicador;
    private final int min;
    private final int max;

    Nivel(double multiplicador, int min, int max) {
        this.multiplicador = multiplicador;
        this.min = min;
        this.max = max;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public static Nivel calcular(int puntosTotales) {
        for (Nivel n : values()) {
            if (puntosTotales >= n.min && puntosTotales <= n.max) {
                return n;
            }
        }
        return BRONCE;
    }
}

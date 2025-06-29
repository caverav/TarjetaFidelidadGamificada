package com.fidelidad.model;

import java.util.Objects;

public class Cliente {
    private final String id;
    private String nombre;
    private String correo;
    private int puntos;
    private Nivel nivel;
    private int streakDias;

    public Cliente(String id, String nombre, String correo) {
        if (!correo.contains("@")) throw new IllegalArgumentException("Correo inválido");
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.puntos = 0;
        this.nivel = Nivel.BRONCE;
        this.streakDias = 0;
    }
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) {
        if (!correo.contains("@")) throw new IllegalArgumentException("Correo inválido");
        this.correo = correo;
    }
    public int getPuntos() { return puntos; }
    public Nivel getNivel() { return nivel; }
    public int getStreakDias() { return streakDias; }
    public void resetStreak() { this.streakDias = 0; }
    public void incrementStreak() { this.streakDias++; }
    public void agregarPuntos(int pts) {
        this.puntos += pts;
        this.nivel = Nivel.calcular(this.puntos);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente c = (Cliente) o;
        return id.equals(c.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
    @Override
    public String toString() {
        return String.format("ID: %s, Nombre: %s, Correo: %s, Puntos: %d, Nivel: %s, Streak: %d días",
          id, nombre, correo, puntos, nivel, streakDias);
    }
}

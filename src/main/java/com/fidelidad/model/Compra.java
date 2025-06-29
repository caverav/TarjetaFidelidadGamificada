package com.fidelidad.model;

import java.time.LocalDate;
import java.util.Objects;

public class Compra {
    private final String idCompra;
    private final String clienteId;
    private final double monto;
    private final LocalDate fecha;

    public Compra(String idCompra, String clienteId, double monto, LocalDate fecha) {
        this.idCompra = idCompra;
        this.clienteId = clienteId;
        this.monto = monto;
        this.fecha = fecha;
    }
    public String getIdCompra() { return idCompra; }
    public String getClienteId() { return clienteId; }
    public double getMonto() { return monto; }
    public LocalDate getFecha() { return fecha; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compra)) return false;
        Compra c = (Compra) o;
        return idCompra.equals(c.idCompra);
    }
    @Override
    public int hashCode() { return Objects.hash(idCompra); }

  @Override
  public String toString() {
      return String.format(
          "ID: %s, Cliente: %s, Monto: %.2f, Fecha: %s",
          getIdCompra(),
          getClienteId(),
          getMonto(),
          getFecha()
      );
  }
}

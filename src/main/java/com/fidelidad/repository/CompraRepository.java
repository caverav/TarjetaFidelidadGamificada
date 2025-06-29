package com.fidelidad.repository;

import com.fidelidad.model.Compra;
import java.util.List;
import java.util.Optional;

public interface CompraRepository {
    void agregar(Compra c);
    Optional<Compra> buscar(String idCompra);
    List<Compra> listar();
    void actualizar(Compra c);
    void eliminar(String idCompra);
}

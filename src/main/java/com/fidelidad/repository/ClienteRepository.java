package com.fidelidad.repository;

import com.fidelidad.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    void agregar(Cliente c);
    Optional<Cliente> buscar(String id);
    List<Cliente> listar();
    void actualizar(Cliente c);
    void eliminar(String id);
}

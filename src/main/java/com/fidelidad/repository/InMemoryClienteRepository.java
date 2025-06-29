package com.fidelidad.repository;

import com.fidelidad.model.Cliente;
import java.util.*;

public class InMemoryClienteRepository implements ClienteRepository {
    private final Map<String, Cliente> store = new HashMap<>();
    @Override public void agregar(Cliente c) { if(store.containsKey(c.getId())) throw new IllegalArgumentException("ID duplicado"); store.put(c.getId(), c);}    
    @Override public Optional<Cliente> buscar(String id) { return Optional.ofNullable(store.get(id)); }
    @Override public List<Cliente> listar() { return new ArrayList<>(store.values()); }
    @Override public void actualizar(Cliente c) { if(!store.containsKey(c.getId())) throw new NoSuchElementException(); store.put(c.getId(), c); }
    @Override public void eliminar(String id) {
      if (store.remove(id) == null) {
          throw new NoSuchElementException("No existe cliente con id " + id);
      }
    }
}

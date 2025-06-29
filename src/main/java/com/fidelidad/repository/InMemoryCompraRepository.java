package com.fidelidad.repository;

import com.fidelidad.model.Compra;
import java.util.*;

public class InMemoryCompraRepository implements CompraRepository {
    private final Map<String, Compra> store = new HashMap<>();
    @Override public void agregar(Compra c) { if(store.containsKey(c.getIdCompra())) throw new IllegalArgumentException("ID duplicado"); store.put(c.getIdCompra(), c);}    
    @Override public Optional<Compra> buscar(String id) { return Optional.ofNullable(store.get(id)); }
    @Override public List<Compra> listar() { return new ArrayList<>(store.values()); }
    @Override public void actualizar(Compra c) { if(!store.containsKey(c.getIdCompra())) throw new NoSuchElementException(); store.put(c.getIdCompra(), c); }
    @Override public void eliminar(String id) { store.remove(id); }
}

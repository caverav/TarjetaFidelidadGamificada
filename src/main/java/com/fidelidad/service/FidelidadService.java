package com.fidelidad.service;

import com.fidelidad.model.Cliente;
import com.fidelidad.model.Compra;
import com.fidelidad.model.Nivel;
import com.fidelidad.repository.ClienteRepository;
import com.fidelidad.repository.CompraRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class FidelidadService {
    private final ClienteRepository clienteRepo;
    private final CompraRepository compraRepo;

    public FidelidadService(ClienteRepository cr, CompraRepository pr) {
        this.clienteRepo = cr;
        this.compraRepo = pr;
    }

    public void agregarCliente(Cliente c) {
        clienteRepo.agregar(c);
    }

    public List<Cliente> listarClientes() {
        return clienteRepo.listar();
    }

    public Cliente obtenerCliente(String id) {
        return clienteRepo.buscar(id).orElseThrow();
    }


    public void actualizarCliente(Cliente c) {
        try {
            clienteRepo.actualizar(c);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("No existe cliente con id " + c.getId());
        }
    }
    public void eliminarCliente(String id) {
        clienteRepo.eliminar(id);
    }

    public void registrarCompra(Compra compra) {
        Cliente c = clienteRepo.buscar(compra.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no existe"));
        int base = (int)(compra.getMonto() / 100);
        LocalDate hoy = compra.getFecha();
        long comprasHoy = compraRepo.listar().stream()
                .filter(p -> p.getClienteId().equals(c.getId()) && p.getFecha().equals(hoy))
                .count();
        c.incrementStreak();
        if (comprasHoy == 2) {
            c.agregarPuntos(10);
        }
        double mult = c.getNivel().getMultiplicador();
        int puntosGanados = (int)Math.floor(base * mult);
        c.agregarPuntos(puntosGanados);
        compraRepo.agregar(compra);
    }

    public List<Compra> listarCompras() {
        return compraRepo.listar();
    }

    public void actualizarCompra(Compra compra) {
        compraRepo.actualizar(compra);
    }

    public void eliminarCompra(String idCompra) {
        compraRepo.eliminar(idCompra);
    }
}

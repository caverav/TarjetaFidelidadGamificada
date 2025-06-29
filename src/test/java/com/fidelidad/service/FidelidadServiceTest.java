package com.fidelidad.service;

import com.fidelidad.model.Cliente;
import com.fidelidad.model.Compra;
import com.fidelidad.model.Nivel;
import com.fidelidad.repository.InMemoryClienteRepository;
import com.fidelidad.repository.InMemoryCompraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FidelidadServiceTest {

    private FidelidadService svc;
    private Cliente cliente;

    @BeforeEach
    void setup() {
        svc = new FidelidadService(
            new InMemoryClienteRepository(),
            new InMemoryCompraRepository()
        );
        cliente = new Cliente("1", "Juan", "j@e.com");
        svc.agregarCliente(cliente);
    }

    @Test
    void registrarCompra_BaseYMultiplicador() {
        svc.registrarCompra(new Compra("p1","1",250, LocalDate.now()));
        assertEquals(2, cliente.getPuntos());

        cliente.agregarPuntos(498);
        assertEquals(Nivel.PLATA, cliente.getNivel());
        svc.registrarCompra(new Compra("p2","1",200, LocalDate.now()));
        assertEquals(502, cliente.getPuntos());
    }

    @Test
    void bonusPorCuartaCompraMismoDia() {
        LocalDate hoy = LocalDate.now();
        for (int i = 1; i <= 4; i++) {
            svc.registrarCompra(new Compra("c"+i,"1",100, hoy));
        }
        assertEquals(14, cliente.getPuntos());
    }

    @Test
    void registrarCompraSinClienteLanza() {
        assertThrows(IllegalArgumentException.class, () ->
            svc.registrarCompra(new Compra("x","nope",100, LocalDate.now()))
        );
    }

    @Test
    void listarComprasYClientes() {
        svc.registrarCompra(new Compra("c1","1",100, LocalDate.now()));
        svc.registrarCompra(new Compra("c2","1",200, LocalDate.now()));
        List<Compra> compras = svc.listarCompras();
        assertEquals(2, compras.size());
        List<Cliente> clientes = svc.listarClientes();
        assertEquals(1, clientes.size());
    }

    @Test
    void actualizarYEliminarCompra() {
        Compra compra = new Compra("u1","1",50, LocalDate.now());
        svc.registrarCompra(compra);
        Compra mod = new Compra("u1","1",500, compra.getFecha());
        svc.actualizarCompra(mod);
        assertEquals(500, svc.listarCompras().get(0).getMonto());

        svc.eliminarCompra("u1");
        assertTrue(svc.listarCompras().isEmpty());
    }
}


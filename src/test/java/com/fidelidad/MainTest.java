package com.fidelidad;

import com.fidelidad.model.Cliente;
import com.fidelidad.service.FidelidadService;
import com.fidelidad.repository.InMemoryClienteRepository;
import com.fidelidad.repository.InMemoryCompraRepository;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final InputStream  origIn  = System.in;
    private final PrintStream  origOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(origOut);
        System.setIn(origIn);
    }

    @Test
    void testSalirInmediato() {
        System.setIn(new ByteArrayInputStream("4\n".getBytes()));
        Main.main(new String[]{});
        String salida = outContent.toString();
        assertTrue(salida.contains("1. Gestión de Clientes"), "Debe mostrar opción 1");
        assertTrue(salida.contains("4. Salir"),             "Debe mostrar opción 4");
    }

    @Test
    void testAgregarYListarClienteYSalir() {
        String input = String.join("\n",
            "1",    // Gestión de Clientes
            "a",    // Agregar
            "Camilo",
            "camilo@fvv.cl",
            "e",    // Volver a menú principal de clientes
            "1",    // Gestión de Clientes
            "b",    // Listar
            "e",    // Volver
            "4"     // Salir
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});
        String salida = outContent.toString();
        assertTrue(salida.contains("a) Agregar"),          "Submenú debe ofrecer 'Agregar'");
        assertTrue(salida.contains("b) Listar"),           "Submenú debe ofrecer 'Listar'");
        assertTrue(salida.contains("Camilo"),              "Debe listar el nombre 'Camilo'");
        assertTrue(salida.contains("camilo@fvv.cl"),     "Debe listar el correo 'camilo@fvv.cl'");
    }

    @Test
    void testMenuClientes() {
        FidelidadService svc = new FidelidadService(
            new InMemoryClienteRepository(),
            new InMemoryCompraRepository()
        );
        String input = String.join("\n",
            "a",
            "TestUser",
            "test@e.com"
        ) + "\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        Main.menuClientes(svc, scanner);
        String salida = outContent.toString();
        System.out.println(salida);
        assertTrue(salida.contains("a) Agregar"),        "Debe mostrar opción Agregar");
        assertTrue(salida.contains("b) Listar"),         "Debe mostrar opción Listar");
        input = String.join("\n",
            "b",
            "e"
        ) + "\n";
        scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        Main.menuClientes(svc, scanner);
        salida = outContent.toString(); 
        assertTrue(salida.contains("TestUser"),          "Debe listar el cliente 'TestUser'"+salida);
        assertTrue(salida.contains("test@e.com"),        "Debe listar el correo 'test@e.com'");
    }

    @Test
    void testMenuCompras() {
        FidelidadService svc = new FidelidadService(
            new InMemoryClienteRepository(),
            new InMemoryCompraRepository()
        );
        // preparar un cliente para registrar compras
        Cliente cliente = new Cliente("ID1", "User1", "u1@e.com");
        svc.agregarCliente(cliente);

        String input = String.join("\n",
            "a",
            cliente.getId(),
            "150"
        ) + "\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        Main.menuCompras(svc, scanner);
        String salida = outContent.toString();
        assertTrue(salida.contains("a) Registrar"),      "Debe mostrar opción Registrar");
        assertTrue(salida.contains("b) Listar"),         "Debe mostrar opción Listar");
        input = String.join("\n",
            "b",
            "e"
        ) + "\n";
        scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        Main.menuCompras(svc, scanner);
        salida = outContent.toString();
        assertTrue(salida.contains("150,00"),            "Debe listar el monto '150,00'");
        assertTrue(salida.contains(cliente.getId()),     "Debe mostrar ID de cliente en la compra");
    }
}


package com.fidelidad;

import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final InputStream origIn = System.in;
    private final PrintStream   origOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(origOut);
        System.setIn(origIn);
    }

    @Test
    void testSalirInmediato() {
        String input = "4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(new String[]{});
        String out = outContent.toString();
        assertTrue(out.contains("1. Gestión de Clientes"), "Debe mostrar opción 1");
        assertTrue(out.contains("4. Salir"),                "Debe mostrar opción 4");
    }

    @Test
    void testAgregarYListarClienteYSalir() {
        StringBuilder sb = new StringBuilder();
        sb.append("1\n");
        sb.append("a\n");
        sb.append("camilo\n");
        sb.append("camilo@fvv.cl\n");
        sb.append("e\n");
        sb.append("1\n");
        sb.append("b\n");
        sb.append("e\n");
        sb.append("4\n");
        System.setIn(new ByteArrayInputStream(sb.toString().getBytes()));
        Main.main(new String[]{});
        String out = outContent.toString();

        assertTrue(out.contains("a) Agregar"), "Submenú debe ofrecer 'Agregar'");
        assertTrue(out.contains("b) Listar"),  "Submenú debe ofrecer 'Listar'");

        assertTrue(out.contains("camilo"),          "Debe listar el nombre 'camilo'");
        assertTrue(out.contains("camilo@fvv.cl"), "Debe listar el correo 'camilo@fvv.cl'");
    }
}

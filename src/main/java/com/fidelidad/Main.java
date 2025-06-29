package com.fidelidad;

import com.fidelidad.model.Cliente;
import com.fidelidad.model.Compra;
import com.fidelidad.service.FidelidadService;
import com.fidelidad.repository.InMemoryClienteRepository;
import com.fidelidad.repository.InMemoryCompraRepository;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FidelidadService svc = new FidelidadService(
            new InMemoryClienteRepository(), new InMemoryCompraRepository()
        );
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Gestión de Clientes\n2. Gestión de Compras\n3. Mostrar Puntos/Nivel\n4. Salir");
            switch (sc.nextLine()) {
                case "1": menuClientes(svc, sc); break;
                case "2": menuCompras(svc, sc); break;
                case "3":
                    System.out.print("ID Cliente: ");
                    String id = sc.nextLine();
                    Cliente c = svc.obtenerCliente(id);
                    System.out.println(c);
                    break;
                case "4": salir = true; break;
                default: System.out.println("Opción inválida");
            }
        }
    }

    private static void menuClientes(FidelidadService svc, Scanner sc) {
        System.out.println("a) Agregar b) Listar c) Actualizar d) Eliminar e) Volver");
        switch (sc.nextLine()) {
            case "a":
                System.out.print("Nombre: "); String nombre = sc.nextLine();
                System.out.print("Correo: "); String correo = sc.nextLine();
                String newId = UUID.randomUUID().toString();
                svc.agregarCliente(new Cliente(newId, nombre, correo));
                break;
            case "b":
                svc.listarClientes().forEach(System.out::println);
                break;
            case "c":
                System.out.print("ID: "); String editId = sc.nextLine();
                Cliente cli = svc.obtenerCliente(editId);
                System.out.print("Nuevo nombre: "); cli.setNombre(sc.nextLine());
                System.out.print("Nuevo correo: "); cli.setCorreo(sc.nextLine());
                svc.actualizarCliente(cli);
                break;
            case "d":
                System.out.print("ID: "); svc.eliminarCliente(sc.nextLine());
                break;
            case "e": return;
            default: System.out.println("Inválido");
        }
    }

    private static void menuCompras(FidelidadService svc, Scanner sc) {
        System.out.println("a) Registrar b) Listar c) Volver");
        switch (sc.nextLine()) {
            case "a":
                System.out.print("ID Cliente: "); String cid = sc.nextLine();
                System.out.print("Monto: "); double monto = Double.parseDouble(sc.nextLine());
                String idc = UUID.randomUUID().toString();
                svc.registrarCompra(new Compra(idc, cid, monto, LocalDate.now()));
                break;
            case "b":
                svc.listarCompras().forEach(System.out::println);
                break;
            case "c": return;
            default: System.out.println("Inválido");
        }
    }
}

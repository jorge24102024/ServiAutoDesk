package serviautodesk;

import java.util.Scanner;

public class ServiAutoDesk {
    static Scanner teclado = new Scanner(System.in);
    static Taller taller = new Taller();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n===== SERVIAUTO DESK =====");
            System.out.println("1. Registrar Vehiculo (y cliente si es nuevo)");
            System.out.println("2. Crear Orden de Reparacion");
            System.out.println("3. Agregar Servicio a Orden");
            System.out.println("4. Avanzar Estado / Facturar Orden");
            System.out.println("5. Pagar y Entregar Vehiculo");
            System.out.println("6. Ver Listas y Reportes");
            System.out.println("7. Salir");
            opcion = leerEntero("Opcion: ", 1, 7);
            
            switch (opcion) {
                case 1: registrarVehiculo(); 
                break;
                case 2: crearOrden(); 
                break;
                case 3: agregarServicio(); 
                break;
                case 4: avanzarEstadoOrden(); 
                break;
                case 5: pagarYEntregar(); 
                break;
                case 6: menuReportes(); 
                break;
                case 7: System.out.println("Hasta luego."); 
                break;
            }
        } while (opcion != 7);
    }

    static void registrarVehiculo() {
        String cedula = leerTexto("Cedula del dueno (10 digitos): ");
        Cliente dueno = taller.buscarCliente(cedula);
        
        if (dueno == null) {
            System.out.println("Cliente nuevo detectado. Ingrese sus datos:");
            String nombre = leerTexto("Nombre del cliente: ");
            String telefono = leerTexto("Telefono: ");
            dueno = new Cliente(cedula, nombre, telefono);
            taller.registrarCliente(dueno);
            System.out.println("Cliente registrado automaticamente.");
        }
        
        if (!dueno.cumpleReglas()) {
            System.out.println(dueno.getMensajeRegla());
            return;
        }
        
        String placa = leerTexto("Placa del vehiculo: ");
        if (taller.buscarVehiculo(placa) != null) {
            System.out.println("Ya existe un vehiculo con esa placa.");
            return;
        }
        
        String marca = leerTexto("Marca: ");
        String modelo = leerTexto("Modelo: ");
        int anio = leerEntero("Anio (1990-2027): ", 1990, 2027);
        int km = leerEntero("Kilometraje: ", 0, 1000000);
        int tipo = leerEntero("Tipo (1. Auto, 2. Moto): ", 1, 2);

        Vehiculo v;
        if (tipo == 1) {
            int puertas = leerEntero("Puertas (2-5): ", 2, 5);
            String comb = leerTexto("Combustible: ");
            v = new Auto(placa, marca, modelo, anio, km, dueno, puertas, comb);
        } else {
            int cil = leerEntero("Cilindrada (cc): ", 50, 2000);
            v = new Moto(placa, marca, modelo, anio, km, dueno, cil);
        }
        
        if (taller.registrarVehiculo(v)) {
            System.out.println("Vehiculo registrado con exito a nombre de " + dueno.getNombre() + ".");
        }
    }

    static void crearOrden() {
        String placa = leerTexto("Placa del vehiculo: ");
        Vehiculo v = taller.buscarVehiculo(placa);
        if (v == null) { System.out.println("Vehiculo no encontrado."); return; }

        String fecha = leerTexto("Fecha (ej: 01/11/2026): ");
        String falla = leerTexto("Falla reportada: ");
        String mec = leerTexto("Mecanico asignado: ");
        
        OrdenReparacion o = taller.crearOrden(v, fecha, falla, mec);
        if (o != null) {
            System.out.println("Orden creada con ID: " + o.getId() + " (Estado: PENDIENTE)");
        }
    }

    static void agregarServicio() {
        int id = leerEntero("ID de la orden: ", 1, 100000);
        OrdenReparacion o = taller.buscarOrden(id);
        if (o == null) { System.out.println("Orden no encontrada."); return; }

        int tipo = leerEntero("Tipo (1. Preventivo, 2. Correctivo): ", 1, 2);
        String desc = leerTexto("Descripcion: ");
        Servicio s;
        if (tipo == 1) {
            double tarifa = leerEntero("Tarifa fija ($): ", 1, 100000);
            s = new MantenimientoPreventivo(desc, tarifa);
        } else {
            double costoH = leerEntero("Costo por hora ($): ", 1, 100000);
            double horas = leerEntero("Horas trabajadas: ", 1, 100);
            double rep = leerEntero("Costo de repuestos ($): ", 0, 1000000);
            s = new MantenimientoCorrectivo(desc, costoH, horas, rep);
        }
        if (o.agregarServicio(s)) {
            System.out.println("Servicio agregado correctamente.");
        }
    }

    static void avanzarEstadoOrden() {
        int id = leerEntero("ID de la orden: ", 1, 100000);
        OrdenReparacion o = taller.buscarOrden(id);
        if (o == null) { System.out.println("Orden no encontrada."); return; }

        if (o.avanzarEstado()) {
            System.out.println("Nuevo estado: " + o.getEstado());
            if (o.getEstado().equals("FINALIZADA")) {
                o.imprimirFactura();
            }
        }
    }

    static void pagarYEntregar() {
        int id = leerEntero("ID de la orden a procesar pago/entrega: ", 1, 100000);
        OrdenReparacion o = taller.buscarOrden(id);
        if (o == null) { 
            System.out.println("Orden no encontrada."); 
            return; 
        
        }

        if (!o.isPagada()) {
            if (o.pagar()) {
                System.out.println("Pago registrado con exito.");
            }
        } else {
            if (o.entregar()) {
                System.out.println("Vehiculo entregado y retirado del taller.");
            }
        }
    }

    static void menuReportes() {
        System.out.println("\n--- REPORTES ---");
        System.out.println("1. Listar Clientes");
        System.out.println("2. Listar Vehiculos");
        System.out.println("3. Listar Ordenes Activas");
        System.out.println("4. Pendientes de Pago o Entrega");
        int op = leerEntero("Opcion: ", 1, 4);
        
        switch(op) {
            case 1: taller.listarClientes(); break;
            case 2: taller.listarVehiculos(); break;
            case 3: taller.listarOrdenesActivas(); break;
            case 4: taller.listarPendientesPagoOEntrega(); break;
        }
    }

    static int leerEntero(String mensaje, int min, int max) {
        int numero;
        do {
            System.out.print(mensaje);
            while (!teclado.hasNextInt()) {
                teclado.next();
                System.out.print("Entrada invalida. " + mensaje);
            }
            numero = teclado.nextInt();
            teclado.nextLine();
            if (numero < min || numero > max) {
                System.out.println("Fuera de rango (" + min + " a " + max + ").");
            }
        } while (numero < min || numero > max);
        return numero;
    }

    static String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = teclado.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("No puede estar vacio.");
            }
        } while (texto.isEmpty());
        return texto;
    }
} 
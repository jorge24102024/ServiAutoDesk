package serviautodesk;

import java.util.ArrayList;

public class Taller {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private ArrayList<OrdenReparacion> ordenes = new ArrayList<>();

    public boolean registrarCliente(Cliente cliente) {
        if (clientes.contains(cliente)) 
            return false;
        clientes.add(cliente);
        return true;
    }

    public Cliente buscarCliente(String cedula) {
        for (Cliente c : clientes) {
            if (c.getCedula().equals(cedula)) return c;
        }
        return null;
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println("Cedula: " + c.getCedula() + " | Nombre: " + c.getNombre() + " | Deuda: $" + c.getSaldoPendiente());
        }
    }

    public boolean registrarVehiculo(Vehiculo vehiculo) {
        if (vehiculos.contains(vehiculo)) {
            System.out.println("Ya existe un vehiculo con esa placa.");
            return false;
        }
        if (!vehiculo.getPropietario().cumpleReglas()) {
            System.out.println(vehiculo.getPropietario().getMensajeRegla());
            return false;
        }
        vehiculos.add(vehiculo);
        return true;
    }

    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) return v;
        }
        return null;
    }

    public void listarVehiculos() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
            return;
        }
        for (Vehiculo v : vehiculos) {
            System.out.println("Placa: " + v.getPlaca() + " | " + v.getMarca() + " " + v.getModelo() + " | Dueno: " + v.getPropietario().getNombre());
        }
    }

    public OrdenReparacion crearOrden(Vehiculo vehiculo, String fecha, String falla, String mecanico) {
        if (vehiculo.isEnTaller()) {
            System.out.println("El vehiculo ya tiene una orden activa.");
            return null;
        }
        OrdenReparacion orden = new OrdenReparacion(vehiculo, fecha, falla, mecanico);
        ordenes.add(orden);
        return orden;
    }

    public OrdenReparacion buscarOrden(int id) {
        for (OrdenReparacion o : ordenes) {
            if (o.getId() == id) return o;
        }
        return null;
    }

    public void listarOrdenesActivas() {
        boolean hay = false;
        for (OrdenReparacion o : ordenes) {
            if (o.isActiva()) {
                System.out.println("Orden #" + o.getId() + " | Placa: " + o.getVehiculo().getPlaca() + " | Estado: " + o.getEstado());
                hay = true;
            }
        }
        if (!hay) System.out.println("No hay ordenes activas.");
    }

    public void listarPendientesPagoOEntrega() {
        boolean hay = false;
        for (OrdenReparacion o : ordenes) {
            if (o.getEstado().equals("FINALIZADA") && !o.isEntregada()) {
                System.out.println("Orden #" + o.getId() + " | Placa: " + o.getVehiculo().getPlaca() + " | Pagada: " + o.isPagada());
                hay = true;
            }
        }
        if (!hay) System.out.println("No hay vehiculos pendientes de pago o entrega.");
    }
}
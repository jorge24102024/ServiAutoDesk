package serviautodesk;

import java.util.ArrayList;

public class OrdenReparacion implements IValidadorReglas {
    private static int contador = 1;
    private int id;
    private Vehiculo vehiculo;
    private String fecha;
    private String falla;
    private String mecanico;
    private String estado; // PENDIENTE, EN_PROCESO, FINALIZADA, CANCELADA
    private ArrayList<Servicio> servicios;
    private boolean pagada;
    private boolean entregada;
    private double iva = 0.15;

    public OrdenReparacion(Vehiculo vehiculo, String fecha, String falla, String mecanico) {
        this.id = contador++;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.falla = falla;
        this.mecanico = mecanico;
        this.estado = "PENDIENTE";
        this.servicios = new ArrayList<>();
        this.pagada = false;
        this.entregada = false;
        vehiculo.setEnTaller(true);
    }

    public int getId() { return id; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public String getEstado() { return estado; }
    public boolean isPagada() { return pagada; }
    public boolean isEntregada() { return entregada; }
    
    public boolean isActiva() {
        return !estado.equals("CANCELADA") && !entregada;
    }

    public boolean agregarServicio(Servicio servicio) {
        if (estado.equals("PENDIENTE") || estado.equals("EN_PROCESO")) {
            servicios.add(servicio);
            return true;
        }
        System.out.println("No se pueden agregar servicios a una orden " + estado + ".");
        return false;
    }

    public double calcularManoObra() {
        double total = 0;
        for (Servicio s : servicios) total += s.getManoObra();
        return total;
    }

    public double calcularRepuestos() {
        double total = 0;
        for (Servicio s : servicios) total += s.getRepuestos();
        return total;
    }

    public double calcularSubtotal() { return calcularManoObra() + calcularRepuestos(); }
    public double calcularIva() { return calcularSubtotal() * iva; }
    public double calcularTotal() { return calcularSubtotal() + calcularIva(); }

    public boolean avanzarEstado() {
        if (estado.equals("PENDIENTE")) {
            estado = "EN_PROCESO";
            return true;
        }
        if (estado.equals("EN_PROCESO")) {
            if (servicios.isEmpty()) {
                System.out.println("Agregue al menos un servicio antes de finalizar.");
                return false;
            }
            estado = "FINALIZADA";
            vehiculo.getPropietario().sumarDeuda(calcularTotal());
            return true;
        }
        System.out.println("La orden esta " + estado + " y no puede avanzar.");
        return false;
    }

    public boolean cancelar() {
        if (estado.equals("PENDIENTE")) {
            estado = "CANCELADA";
            vehiculo.setEnTaller(false);
            return true;
        }
        System.out.println("Solo se pueden cancelar ordenes PENDIENTES.");
        return false;
    }

    public boolean pagar() {
        if (!estado.equals("FINALIZADA")) {
            System.out.println("Solo se pueden pagar ordenes FINALIZADAS.");
            return false;
        }
        if (pagada) {
            System.out.println("Esta orden ya fue pagada.");
            return false;
        }
        pagada = true;
        vehiculo.getPropietario().restarDeuda(calcularTotal());
        return true;
    }

    @Override
    public boolean cumpleReglas() {
        return estado.equals("FINALIZADA") && pagada;
    }

    @Override
    public String getMensajeRegla() {
        return "No se puede entregar: la orden debe estar FINALIZADA y pagada.";
    }

    public boolean entregar() {
        if (!cumpleReglas()) {
            System.out.println(getMensajeRegla());
            return false;
        }
        entregada = true;
        vehiculo.setEnTaller(false);
        return true;
    }

    public void imprimirFactura() {
        System.out.println("\n===== ORDEN #" + id + " =====");
        System.out.println("Fecha: " + fecha + " | Estado: " + estado);
        System.out.println("Cliente: " + vehiculo.getPropietario().getNombre());
        System.out.println("Vehiculo: " + vehiculo.getPlaca() + " | Mecanico: " + mecanico);
        System.out.println("Falla: " + falla);
        for (Servicio s : servicios) {
            System.out.println(" - " + s.getDescripcion() + " -> $" + s.calcularCosto());
        }
        System.out.println("Mano de obra: $" + calcularManoObra());
        System.out.println("Repuestos:    $" + calcularRepuestos());
        System.out.println("Subtotal:     $" + calcularSubtotal());
        System.out.println("IVA (15%):    $" + calcularIva());
        System.out.println("TOTAL:        $" + calcularTotal());
    }
}
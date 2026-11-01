package serviautodesk;

public class Cliente extends Persona implements IValidadorReglas {
    private double saldoPendiente;

    public Cliente(String cedula, String nombre, String telefono) {
        super(cedula, nombre, telefono);
        this.saldoPendiente = 0;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void sumarDeuda(double monto) {
        saldoPendiente = saldoPendiente + monto;
    }

    public void restarDeuda(double monto) {
        saldoPendiente = saldoPendiente - monto;
        if (saldoPendiente < 0.01) {
            saldoPendiente = 0;
        }
    }

    public boolean tienePagoPendiente() {
        return saldoPendiente > 0;
    }

    // Regla: con pago pendiente no puede registrar otro vehiculo
    @Override
    public boolean cumpleReglas() {
        return !tienePagoPendiente();
    }

    @Override
    public String getMensajeRegla() {
        return "El cliente tiene un pago pendiente de $" + saldoPendiente
                + ". No puede registrar otro vehiculo.";
    }

    @Override
    public String toString() {
        return super.toString() + " | Saldo pendiente: $" + saldoPendiente;
    }
}
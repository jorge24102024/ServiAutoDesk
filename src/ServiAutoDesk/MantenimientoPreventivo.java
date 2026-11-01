package serviautodesk;

public class MantenimientoPreventivo extends Servicio {

    public MantenimientoPreventivo(String descripcion, double tarifaFija) {
        super(descripcion, tarifaFija);
    }

    @Override
    public double getManoObra() {
        return getCostoBase();
    }

    @Override
    public double getRepuestos() {
        return 0;
    }

    @Override
    public String toString() {
        return "Preventivo: " + super.toString();
    }
}
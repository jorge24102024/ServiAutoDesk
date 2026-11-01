package serviautodesk;

public class MantenimientoCorrectivo extends Servicio {
    private double horas;
    private double costoRepuestos;

    // costoBase = costo por hora
    public MantenimientoCorrectivo(String descripcion, double costoPorHora,
            double horas, double costoRepuestos) {
        super(descripcion, costoPorHora);
        this.horas = horas;
        this.costoRepuestos = costoRepuestos;
    }

    @Override
    public double getManoObra() {
        return getCostoBase() * horas;
    }

    @Override
    public double getRepuestos() {
        return costoRepuestos;
    }

    @Override
    public String toString() {
        return "Correctivo: " + super.toString();
    }
}
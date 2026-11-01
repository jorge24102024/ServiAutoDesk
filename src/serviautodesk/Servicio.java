package serviautodesk;

public abstract class Servicio {
    private String descripcion;
    private double costoBase; // tarifa fija (preventivo) o costo por hora (correctivo)

    public Servicio(String descripcion, double costoBase) {
        this.descripcion = descripcion;
        this.costoBase = costoBase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(double costoBase) {
        if (costoBase > 0) {
            this.costoBase = costoBase;
        }
    }

    // Cada tipo de servicio los calcula a su manera (polimorfismo)
    public abstract double getManoObra();

    public abstract double getRepuestos();

    public double calcularCosto() {
        return getManoObra() + getRepuestos();
    }

    @Override
    public String toString() {
        return descripcion + " -> $" + calcularCosto();
    }
}
    
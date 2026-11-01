package serviautodesk;

public class Auto extends Vehiculo {
    private int puertas;
    private String combustible;

    public Auto(String placa, String marca, String modelo, int anio, int km,
            Cliente propietario, int puertas, String combustible) {
        super(placa, marca, modelo, anio, km, propietario);
        this.puertas = puertas;
        this.combustible = combustible;
    }

    public int getPuertas() {
        return puertas;
    }

    public String getCombustible() {
        return combustible;
    }

    @Override
    public String toString() {
        return "AUTO | " + super.toString() + " | Puertas: " + puertas + " | " + combustible;
    }
}
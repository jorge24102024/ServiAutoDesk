package serviautodesk;

public class Moto extends Vehiculo {
    private int cilindrada;

    public Moto(String placa, String marca, String modelo, int anio, int km,
            Cliente propietario, int cilindrada) {
        super(placa, marca, modelo, anio, km, propietario);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    @Override
    public String toString() {
        return "MOTO | " + super.toString() + " | Cilindrada: " + cilindrada + "cc";
    }
}
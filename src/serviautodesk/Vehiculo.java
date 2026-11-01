package serviautodesk;

public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private int km;
    private Cliente propietario;
    private boolean enTaller; // true mientras tenga una orden activa

    public Vehiculo(String placa, String marca, String modelo, int anio, int km, Cliente propietario) {
        this.placa = placa.toUpperCase();
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.km = km;
        this.propietario = propietario;
        this.enTaller = false;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        if (anio >= 1990 && anio <= 2027) {
            this.anio = anio;
        }
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        if (km >= 0) {
            this.km = km;
        }
    }

    public Cliente getPropietario() {
        return propietario;
    }

    public boolean isEnTaller() {
        return enTaller;
    }

    public void setEnTaller(boolean enTaller) {
        this.enTaller = enTaller;
    }

    // Dos vehiculos son iguales si tienen la misma placa
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehiculo) {
            Vehiculo otro = (Vehiculo) obj;
            return this.placa.equals(otro.placa);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + " | " + marca + " " + modelo + " | Anio: " + anio
                + " | Km: " + km + " | Dueno: " + propietario.getNombre();
    }
}
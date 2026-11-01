package serviautodesk;

public abstract class Persona {
    private String cedula;
    private String nombre;
    private String telefono;

    public Persona(String cedula, String nombre, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Dos personas son iguales si tienen la misma cedula
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Persona otra = (Persona) obj;
        return this.cedula.equals(otra.cedula);
    }

    @Override
    public String toString() {
        return "Cedula: " + cedula + " | Nombre: " + nombre + " | Telefono: " + telefono;
    }
}
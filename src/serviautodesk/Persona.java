package serviautodesk;

public abstract class Persona {
    private String cedula;
    private String nombre;
    private String telefono;

    public Persona(String cedula, String nombre, String telefono) {
        setCedula(cedula);
        setNombre(nombre);
        setTelefono(telefono);
    }

    public String getCedula() {
        return cedula;
    }
    //VALIDACION DE CEDULA
    public void setCedula(String cedula) {
        if(cedula!= null && cedula.matches("[0-9]{10}")) {
            this.cedula = cedula;
        } else {
            System.out.println("Error: La cedula debe tener 10 digitos.");
        }
    }

    public String getNombre() {
        return nombre;
    }
    //VALIDACION NOMBRE
    public void setNombre(String nombre) {
         this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }
    //VALIDACION TELEFONO
    public void setTelefono(String telefono) {
           this.telefono=telefono;
    }

    @Override
    public String toString() {
        return "Cedula: " + cedula + " | Nombre: " + nombre + " | Telefono: " + telefono;
    }
}
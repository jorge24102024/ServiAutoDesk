package serviautodesk;

public abstract class Persona {
    private String cedula;
    private String nombre;
    private String apellido;
    private String celular;
    
    //Constructor
    public Persona(String cedula, String nombre, String apellido, String celular){
        this.cedula= cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular= celular;
    }

}

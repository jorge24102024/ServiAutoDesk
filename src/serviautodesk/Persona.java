package serviautodesk;

public abstract class Persona {
    private String cedula;
    private String nombre;
    private String apellido;
    private String celular;
    
    //Constructor
    public Persona(String cedula, String nombre, String apellido, String celular){
        setCedula(cedula);
        setNombre(nombre);
        setApellido(apellido);
        setCelular(celular);
    }
    
    public String getCedula() {
        return cedula;
    }
    //Validar q no ingrese una cedula nula y tenga 10 digitos 
    public void setCedula(String cedula) {
        if (cedula!=null && cedula.length()==10){
            this.cedula = cedula;
        }else{
            System.out.println("Cedula Invalida....."); 
       }
    } 

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre!=null && !nombre.isBlank()){
            nombre = nombre.trim();
            this.nombre = nombre;
        }else{
            System.out.println("Nombre Invalido....."); 
       }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
         if (apellido!=null && !apellido.isBlank()){
            apellido = apellido.trim();
            this.apellido = apellido;
        }else{
            System.out.println("Apellido Invalido....."); 
       }
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        if (celular!=null && celular.length()==10){
            this.celular = celular;
        }else{
            System.out.println("Celular Invalida....."); 
       }
    }

}

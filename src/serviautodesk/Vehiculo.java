
package serviautodesk;


public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    protected Cliente propietario;

    public Vehiculo(String placa, String marca, String modelo, int anio, Cliente propietario) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.propietario = propietario;
    }
    /*
     No conocemos estos metodos pero los pongo ya que estaba con duda el hacer esta clase y que metodos nesecitaria
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
        return true;
        }
        
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
      
        Vehiculo otroVehiculo = (Vehiculo) obj;
    
    
        return this.placa.equals(otroVehiculo.placa);

    }
    @Override
    public String toString() {
        return "Vehículo {" + "placa=" + placa +", marca=" + marca + ", modelo=" + modelo + ", año=" + anio +'}';
            
            
            
             
    }
          
              
    
}

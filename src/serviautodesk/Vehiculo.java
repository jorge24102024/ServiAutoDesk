
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

    public Cliente getPropietario() {
        return propietario;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnio(int anio) {
        if (anio >= 2027 && 1990<= anio ){
            
            this.anio = anio;
        }
    }

    public void setPropietario(Cliente propietario) {
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

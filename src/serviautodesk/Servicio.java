package serviautodesk;

//clase abstracta servicio
public abstract class Servicio {
   private String descripcion;
   private double costoBase;
   
   //Constructor
   public Servicio(String descripcion, double costoBase){
       this.descripcion= descripcion;
       this.costoBase= costoBase;
   }
   
   public String getDescripcion(){
       return descripcion;
   }
   
   public void setDescripcion(String descripcion){
       this.descripcion=descripcion;
   }
   
   public double getCostoBase(){
       return costoBase;
   }
   
   public void setCostoBase(double costoBase){
       if(costoBase >0){
           this.costoBase= costoBase;
       }else{
           System.out.println("Error. El costoBase debe ser mayor a $0");
       }
   }
   
   //metodos abstractos
   public abstract double calcularCosto();
}

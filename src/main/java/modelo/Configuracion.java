package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Configuracion implements Serializable,Comparable<Configuracion>{
    
    //Atributos de clase
    private boolean visibilidadCarta;
    private int maxCantOponentes;    
    
    //Constructor
    public Configuracion(boolean visibilidadCarta, int maxcantidad)
    {
        this.visibilidadCarta = visibilidadCarta;
        this.maxCantOponentes = maxcantidad;        
    }    

    //Metodos Getter and Setter
    public boolean getVisibilidadCarta() {
        return visibilidadCarta;
    }

    public void setVisibilidadCarta(boolean visibilidadCarta) {
        this.visibilidadCarta = visibilidadCarta;
    }

    public int getMaxCantOponentes() {
        return maxCantOponentes;
    }

    public void setMaxCantOponentes(int maxCantOponentes) {
        this.maxCantOponentes = maxCantOponentes;
    }
    
   
    @Override
    public int compareTo(Configuracion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Metodos de Clase
    public static void serializarConfiguracion(Configuracion conf, String ruta){
        try(ObjectOutputStream a = new ObjectOutputStream(new FileOutputStream(ruta))){
            a.writeObject(conf);
        }catch(IOException e){}
    }
        
    public static Configuracion desserializarConfiguracion(String ruta){
        try(ObjectInputStream a = new ObjectInputStream(new FileInputStream(ruta))){
            return (Configuracion) a.readObject();
        }catch(Exception e)
        {            
            return new Configuracion(false, 0);
        }
    }
    
    public static void guardarConfiguracion(Configuracion conf) throws Exception{        
        Configuracion.serializarConfiguracion(conf, "Configuracion.ser");        
    }
    
}

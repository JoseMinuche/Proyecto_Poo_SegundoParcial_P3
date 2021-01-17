
package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Reporte implements Serializable,Comparable<Reporte>{
    
    //Atributos de clase
    private Date fechaReporte;
    private int duracionJuegoMinutos;
    private String jugador;
    private int cantOponente;    
    private String alineacion;
    
    //Constructor
    public Reporte()
    {               
    }
    
    public Reporte(Date fechaReporte, int duracionJuegoMinutos, String jugador, int cantOponente,String alineacion) {
        this.fechaReporte = fechaReporte;
        this.duracionJuegoMinutos = duracionJuegoMinutos;
        this.jugador = jugador;
        this.cantOponente = cantOponente;
        this.alineacion = alineacion;
    }  
    
    //Metodos Getter and Setter
    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public int getDuracionJuegoMinutos() {
        return duracionJuegoMinutos;
    }

    public void setDuracionJuegoMinutos(int duracionJuegoMinutos) {
        this.duracionJuegoMinutos = duracionJuegoMinutos;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public int getCantOponente() {
        return cantOponente;
    }

    public void setCantOponente(int cantOponente) {
        this.cantOponente = cantOponente;
    }

    
    public String getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(String alineacion) {
        this.alineacion = alineacion;
    }
    
    
    
    //Metodos de Clase
    public static void serializarReporte(ArrayList<Reporte> listap, String ruta){
        try(ObjectOutputStream a = new ObjectOutputStream(new FileOutputStream(ruta))){
            a.writeObject(listap);
        }catch(IOException e){}
    }
        
    public static ArrayList<Reporte> desserializarReporte(String ruta){
        try(ObjectInputStream a = new ObjectInputStream(new FileInputStream(ruta))){
            return (ArrayList<Reporte>) a.readObject();
        }catch(Exception e)
        {
            System.out.println(e);
            return new ArrayList<>();
        }
    }
    
    public static void guardarReporte(Reporte reporte) throws Exception{
        ArrayList<Reporte> lista= Reporte.desserializarReporte("Reporte.ser");
        lista.add(reporte);
        Reporte.serializarReporte(lista, "Reporte.ser");        
    }

    @Override
    public int compareTo(Reporte o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
}

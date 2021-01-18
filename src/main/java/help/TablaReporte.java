
package help;

import javafx.beans.property.SimpleStringProperty;

public class TablaReporte 
{
    private SimpleStringProperty fecha;
    private SimpleStringProperty duracion;
    private SimpleStringProperty jugador;
    private SimpleStringProperty oponentes;
    private SimpleStringProperty alineacion;
    
    //Constructor
    public TablaReporte(String fecha,String duracion, String jugador, String oponentes, String alineacion) {
        this.fecha = new SimpleStringProperty(fecha);
        this.duracion = new SimpleStringProperty(duracion);
        this.jugador = new SimpleStringProperty(jugador);
        this.oponentes = new SimpleStringProperty(oponentes);
        this.alineacion = new SimpleStringProperty(alineacion);
    }        

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(SimpleStringProperty fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion.get();
    }

    public void setDuracion(SimpleStringProperty duracion) {
        this.duracion = duracion;
    }

    public String getJugador() {
        return jugador.get();
    }

    public void setJugador(SimpleStringProperty jugador) {
        this.jugador = jugador;
    }

    public String getOponentes() {
        return oponentes.get();
    }

    public void setOponentes(SimpleStringProperty oponentes) {
        this.oponentes = oponentes;
    }

    public String getAlineacion() {
        return alineacion.get();
    }

    public void setAlineacion(SimpleStringProperty alineacion) {
        this.alineacion = alineacion;
    }
    
    
    
}

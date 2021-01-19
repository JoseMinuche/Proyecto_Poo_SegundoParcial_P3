package modelo;

import java.io.Serializable;
import java.util.Optional;
import javafx.scene.image.Image;

public class Carta implements Serializable,Comparable<Carta>
{
    //Atributos de clase
    private int numero;
    private String nombre;
    private String urlImagenCarta;
    private Image imagenCarta;
    private Image imagenCartaSeleccionada;
    private Image imagenCartaSeleccionadaPC;
    private Image imagenCartaBack;
    private boolean esSeleccionado;
    
    //Constructor
    public Carta(int numero, String nombre, 
                 String urlImagenCarta, 
                 Image imagenCarta,
                 Image imagenCartaSeleccionada,
                 Image imagenCartaSeleccionadaPC,
                 Image imagenCartaBack) {
        this.numero = numero;
        this.nombre = nombre;
        this.urlImagenCarta = urlImagenCarta;
        this.imagenCarta = imagenCarta;
        this.imagenCartaSeleccionada = imagenCartaSeleccionada;
        this.imagenCartaSeleccionadaPC = imagenCartaSeleccionadaPC;
        this.imagenCartaBack = imagenCartaBack;
    }
    
    //Getter and Setter
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagenCarta() {
        return urlImagenCarta;
    }

    public void setUrlImagenCarta(String urlImagenCarta) {
        this.urlImagenCarta = urlImagenCarta;
    }

    public Image getImagenCarta(int jugadorSeleccionado, boolean mostrarCarta) 
    {   
        //JUGADOR HUMANO
        if(jugadorSeleccionado == 1)
        {
            if(esSeleccionado)
                return imagenCartaSeleccionada;
            else
                return imagenCarta;
        }
        else
        {
            //JUGADORES COMPUTADOR 1 Y 2
            if(esSeleccionado)
                return imagenCartaSeleccionadaPC;
            else
                if(mostrarCarta)
                    return imagenCarta;
                else 
                    return imagenCartaBack;            
        }         
    }

    public void setImagenCarta(Image imagenCarta) {
        this.imagenCarta = imagenCarta;
    }

    public boolean getCartaSeleccionada() {
        return esSeleccionado;
    }

    public void setCartaSeleccionada(boolean esSeleccionado) {
        this.esSeleccionado = esSeleccionado;
    }    

    public Image getImagenCartaSeleccionadoPC() {
        return imagenCartaSeleccionadaPC;
    }

    public void setImagenCartaSeleccionadoPC(Image imagenNaipeSeleccionadoPC) {
        this.imagenCartaSeleccionadaPC = imagenNaipeSeleccionadoPC;
    }
    
    @Override
    public int compareTo(Carta o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }        
}


package modelo;

import java.util.ArrayList;

public class Juego {

    //Atributos de clase
    private ArrayList<Jugador> jugadores;   
    private ArrayList<Carta> masoCartas;
    private Alineacion alineacion;
    private int ordenCarta;
    private Carta cartaActual;
    
    //Constructor
    public Juego(ArrayList<Jugador> jugadores,Alineacion alineacion, ArrayList<Carta> cartas) {
        this.jugadores = jugadores;        
        this.alineacion = alineacion;
        this.masoCartas = cartas;
    }
    
    //Getter and Setter
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    public Alineacion getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(Alineacion alineacion) {
        this.alineacion = alineacion;
    }

    public ArrayList<Carta> getMasoCartas() {
        return masoCartas;
    }

    public void setMasoNaipes(ArrayList<Carta> masoCarta) {
        this.masoCartas = masoCarta;
    }

    public int getOrdenCarta() {
        return ordenCarta;
    }

    public void setOrdenCarta(int ordenCarta) {
        this.ordenCarta = ordenCarta;
    }

    public Carta getNaipeActual() {
        return cartaActual;
    }

    public void setCartaActual(Carta cartaActual) {
        this.cartaActual = cartaActual;
    }        
}


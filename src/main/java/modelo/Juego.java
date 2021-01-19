package modelo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Juego {

    //Atributos de clase
    private ArrayList<Jugador> jugadores;   
    private ArrayList<Carta> masoCartas;
    private Alineacion alineacion;
    private int ordenCarta;
    private Carta cartaActual;
    private boolean running;
    private long duracion;
    private final LocalTime tiempoInicio;
    private Jugador ganador;
    private int idx;
    
    //Constructor
    public Juego(ArrayList<Jugador> jugadores,Alineacion alineacion, ArrayList<Carta> cartas) {
        this.jugadores = jugadores;        
        this.alineacion = alineacion;
        this.masoCartas = cartas;
        tiempoInicio = LocalTime.now();
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
    
    public static List<Juego> cargarJuegos() 
    {
        try (var in = new ObjectInputStream(new FileInputStream("Juego.ser"))) {
            return (List<Juego>)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
    
    public void finish(Jugador jugador) {
        duracion = Duration.between(tiempoInicio, LocalTime.now()).toSeconds();
        ganador = jugador;
        running = false;
    }
    
    public void finish() {
        running = false;
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public boolean verificarVictoria(Jugador jugador) {
        boolean won = help.HelperJuego.verificarJuegoGanado(jugador.getTablero(), alineacion);
        if (won) finish(jugador);
        return won;
    }
    
    public boolean siguienteCarta() {
        Optional<Carta> carta;
        if ((carta = nextCarta()).isPresent()) { // Using an Optional here is dumb, same as checking for null
            cartaActual = carta.get();
            return true;
        } else {
            finish();
            return false;
        }
    }

    public Optional<Carta> nextCarta() {
        if (idx < masoCartas.size()) {
            return Optional.of(masoCartas.get(idx++));
        } else {
            return Optional.empty();
        }
    }
}


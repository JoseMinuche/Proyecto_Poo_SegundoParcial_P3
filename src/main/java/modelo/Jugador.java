package modelo;

import java.io.Serializable;

public class Jugador implements Serializable,Comparable<Jugador>
{
    //Atributos de clase
    private String nombre;    
    private Carta[][] tablero;
    
    //Constructor       
    public Jugador(String nombre, Carta[][] tablero) {
        this.nombre = nombre;        
        this.tablero = tablero;
    }
    
    //Getter and Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carta[][] getTablero() {
        return tablero;
    }

    public void setTablero(Carta[][] tablero) {
        this.tablero = tablero;
    }
    
    @Override
    public int compareTo(Jugador o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean marcarCarta(Carta cartaActual) 
    {        
        for(int i = 0; i < 16 ; i++)
        {
            int row = i / 4;
            int col = i % 4;
            var carta = tablero[row][col];
            if(carta.getNumero() == cartaActual.getNumero())            
               return true;                                                                
        }         
        return false;                
    }
}

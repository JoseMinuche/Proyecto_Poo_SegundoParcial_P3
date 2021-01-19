/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.proyectoloteriamexicana.App;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Alineacion;
import modelo.Carta;
import modelo.Juego;
import modelo.Jugador;

/**
 * FXML Controller class
 *
 * @author Octavio Lucero
 */
public class InicioJuegoController implements Initializable {

    @FXML
    private TextField txt_NombreJugador;
    @FXML
    private Button btnComenzarJuego;

    Alineacion alineacionGanadora;
    String nombreJugador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }    

    @FXML
    private void ComenzarJuego(ActionEvent event) 
    {
        nombreJugador = txt_NombreJugador.getText().trim();
        if(nombreJugador.isEmpty())
           help.HelperJuego.showMessage(new Alert(Alert.AlertType.WARNING),"Inicio de Juego",null,"Debe ingresar un nombre!");
        else
        {
            alineacionGanadora = help.HelperJuego.generarAlineacionGanadora();
            cargarPanelJuego();
        }       
    }
                
    public void cargarPanelJuego()
    {
        Carta[][] tableroJugadorHumano = help.HelperJuego.generarTableroJugador();
        Jugador jugadorHumano = new Jugador(nombreJugador,tableroJugadorHumano);                
        
        Carta[][] tableroJugadorPC1 = null;
        boolean tableroIgual = true;
        while(tableroIgual)
        {             
            tableroJugadorPC1 = help.HelperJuego.generarTableroJugador();
            if(!tableroJugadorHumano.equals(tableroJugadorPC1))
                tableroIgual = false;
        }                
        Jugador jugadorPC1 = new Jugador("Jugador PC 1",tableroJugadorPC1);
        
        Carta[][] tableroJugadorPC2 = null;
        tableroIgual = true;
        while(tableroIgual)
        {             
            tableroJugadorPC2 = help.HelperJuego.generarTableroJugador();
            if(!tableroJugadorPC1.equals(tableroJugadorPC2))
                tableroIgual = false;
        }                
        Jugador jugadorPC2 = new Jugador("Jugador PC 2",tableroJugadorPC2);
                
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugadorHumano);
        jugadores.add(jugadorPC1);
        jugadores.add(jugadorPC2);
        
        ArrayList<Carta> masoJuego = help.HelperJuego.generarMasoNaipes();        
        Juego juego = new Juego(jugadores,alineacionGanadora,masoJuego);   
        System.out.println("termino el proceso");
        mostrarPanelJuego(juego);
    }
    
    public void mostrarPanelJuego(Juego juego)
    {
        try
        {
            //Muestra la pantalla del juego comenzado
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("JuegoV2.fxml"));     
            Parent root = (Parent)fxmlLoader.load();                  
            JuegoController controlador = fxmlLoader.<JuegoController>getController();                                
            controlador.cargarJuego(juego);
            Stage stage = new Stage();
            stage.setTitle("Juego Comenzado");
            stage.setScene(new Scene(root, 1150, 750));
            stage.show();  
            Stage stageActual = (Stage) txt_NombreJugador.getScene().getWindow();            
            stageActual.close();           
            System.out.println("Probado ahora");
        }
        catch(Exception e)
        {
                       
        }
        
    }
    
}

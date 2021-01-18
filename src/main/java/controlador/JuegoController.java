package controlador;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import modelo.Juego;

public class JuegoController implements Initializable {

    @FXML
    private ImageView imagenAlineacion;
    @FXML
    private Label nombreAlineacion;
    @FXML
    private HBox paneOponentes;
    @FXML
    private GridPane gridUsuario;
    @FXML
    private ImageView imagenCartaActual;
    @FXML
    private Button menuPrincipalBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void cargarJuego(Juego juego) 
    {
        /*horaInicio = new Date();
        juegoActual = juego;
        cantJugadores = juegoActual.getJugadores().size();
        
        configuracion = HelperJuego.getConfiguracion();        
        alineacionGanadora = juegoActual.getAlineacion();
        jugadorHumano = juegoActual.getJugadores().get(0);
        tableroJugadorHumano = jugadorHumano.getTablero();

        jugadorPC1 = juegoActual.getJugadores().get(1);
        tableroJugadorPC1 = jugadorPC1.getTablero();
                
        if(cantJugadores > 2)
        {
            jugadorPC2 = juegoActual.getJugadores().get(2);
            tableroJugadorPC2 = jugadorPC2.getTablero();
        }
        
        masoNaipe = juegoActual.getMasoNaipes();
        naipeActual = masoNaipe.get(0);    
        tableroActual = tableroJugadorHumano;     
        
        cargarConfiguracion();
        cargarDatosJuego();
        iniciarConteoRegresivoNaipe();
        */  
    }

    @FXML
    private void verificarVictoriaUsuario(ActionEvent event) {
    }

    @FXML
    private void cargarMenuPrincipal(ActionEvent event) {
    }
    
}

package controlador;

import help.CartaBlitter;
import help.CartasNoVisiblesBlitter;
import help.StdBlitter;
import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modelo.Alineacion;
import modelo.Carta;
import modelo.Configuracion;
import modelo.Juego;
import modelo.Jugador;
import modelo.Reporte;

public class JuegoController implements Initializable {
    
    @FXML
    private HBox paneOponentes;
    @FXML
    private GridPane gridUsuario;
    private ImageView imagenCartaActual;

    Carta[][] tableroHumano = new Carta[4][4];
    Jugador jugadorHumano;
    Jugador jugadorPC1;
    Jugador jugadorPC2;
    
    Date horaInicio;
    Date horaFin;
    Juego juegoActual;
    Alineacion alineacionGanadora;
    ArrayList<Juego> juegos;
    ArrayList<Jugador> jugadores;
    ArrayList<Carta> masoCartas;
    Configuracion configuracion;
    int cantidadJugadores;
    ArrayList<Jugador> oponentes = new ArrayList<>();
    Carta[][] tableroActual = new Carta[4][4];
    int cantCartaJuego;
    Carta cartaActual;
    boolean juegoCorriendo = false;
    static int interval;
    static Timer timer;
        
    @FXML
    private ImageView img_AlineacionGanadora;
    @FXML
    private Label lbl_tablero_actual;
    @FXML
    private ImageView img_NaipeActual;
    @FXML
    private Button btn_CambiarNaipe;
    @FXML
    private Label lbl_tiempo_regresivo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void cargarJuego(Juego juego) 
    {
        horaInicio = new Date();                
        juegoActual = juego;
        jugadores = juegoActual.getJugadores();
        alineacionGanadora = juegoActual.getAlineacion();
        cantidadJugadores = jugadores.size();
        masoCartas = juegoActual.getMasoCartas();
        juegoCorriendo = true;
        cartaActual = juegoActual.getMasoCartas().get(0);
        cargarImagenCartaInicial();                
        
        if(cantidadJugadores == 2)
        {
            jugadorPC1 = jugadores.get(1);
            oponentes.add(jugadores.get(1));
        }
        if(cantidadJugadores == 3)      
        {
            jugadorPC1 = jugadores.get(1);
            jugadorPC2 = jugadores.get(2);
            oponentes.add(jugadores.get(1));
            oponentes.add(jugadores.get(2));
        }   
                       
        configuracion = help.HelperJuego.cargarConfiguracion();  
        jugadorHumano = juegoActual.getJugadores().get(0);
        tableroHumano = jugadorHumano.getTablero();        
        
        
        updateTabla(tableroHumano,  gridUsuario,new StdBlitter(Configuracion.anchoCartaUsuario,this::verificarMarca));                
        updateAlineacion(juegoActual.getAlineacion());
        
        updateTablasOponentes();
        spawnThread(this::unleashComputadora, true);                
        iniciarConteoRegresivoNaipe();
    }

    private void cargarImagenCartaInicial()
    {
        String imagenCartaActual = "src/main/resources/imagenesNaipe/"+juegoActual.getMasoCartas().get(0).getUrlImagenCarta();   
        System.out.println(imagenCartaActual);
        img_NaipeActual.setImage(new Image(new File(imagenCartaActual).toURI().toString())); 
    }        
    
    private void updateAlineacion(Alineacion alineacion) 
    {        
        String alineacionString = help.HelperJuego.getAlineacionGanadora(alineacionGanadora);
        String alineacionImagen = "src/main/resources/formaGanar/"+alineacionString;   
        System.out.println("alineacionImagen=>"+alineacionImagen);
        img_AlineacionGanadora.setImage(new Image(new File(alineacionImagen).toURI().toString()));
    }
    
    private void cambiarNaipe()
    {        
        //Evita regresar al mismo naipe
        if(cantCartaJuego < 54)
        {                        
            cantCartaJuego++;           
            cargarNaipeActual();
            cargarImagenNaipeActual();
            iniciarConteoRegresivoNaipe();
        }
        else
            help.HelperJuego.showMessage(new Alert(Alert.AlertType.ERROR),"Mensaje del Sistema.. ",null,"Se han acabado los naipes!");                                                     
    }
    
    public void cargarNaipeActual()
    {
        if(cantCartaJuego < 54)
        {
            juegoActual.setCartaActual(masoCartas.get(cantCartaJuego));
            cartaActual = juegoActual.getNaipeActual();                
            System.out.println("Carta Actual =>"+cartaActual.getNombre());
        }
    }
    
    public void cargarImagenNaipeActual()
    {
        String cartaActual = "src/main/resources/imagenesNaipe/"+juegoActual.getNaipeActual().getUrlImagenCarta();   
        img_NaipeActual.setImage(new Image(new File(cartaActual).toURI().toString()));                                 
    }
    
    private void mostrarConteoRegresivo(int interval)
    {
        lbl_tiempo_regresivo.setText(String.valueOf(interval));
    }
    
    private void iniciarConteoRegresivoNaipe()
    {
        interval = 4;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(interval > 0)
                {
                    Platform.runLater(() -> mostrarConteoRegresivo(interval));                    
                    interval--;
                }
                else
                {
                    timer.cancel();
                    Platform.runLater(() -> cambiarNaipe());
                }                    
            }
        }, 1000,1000);
    }
    
    private void updateTabla(Carta[][] tabla, GridPane grid, CartaBlitter blitter) {
        grid.getChildren().clear();
        Node node;
        for(int i = 0; i < 16 ; i++)
        {
            int row = i / 4;
            int col = i % 4;
            var carta = tabla[row][col];
            try
            {
                node = blitter.prepararCarta(tabla, carta);
                grid.add(node,col,row);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }                        
        }        
    }
    
    private void verificarMarca(MouseEvent e, Carta carta) {
        var tabla = jugadorHumano.getTablero();
        System.out.println("Carta a verificar=>"+carta.getNombre());        
        
        //Si la carta es la escogida y no ha sido seleccionada previamente
        if(carta.getNumero() == cartaActual.getNumero() && !carta.getCartaSeleccionada()) 
        {
            actualizarTablero(carta);
            spawnThread(() -> anadirBean((Pane)e.getSource(), Configuracion.anchoCartaUsuario / 2), true);                    
        }        
        else if(!carta.getCartaSeleccionada())
            spawnThread(() -> showX((Pane)e.getSource(), Configuracion.anchoCartaUsuario), true);               
    }
    
    private void actualizarTablero(Carta carta)
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4 ; j++)
            {
                if(tableroHumano[i][j].getNumero() == carta.getNumero())                
                    tableroHumano[i][j].setCartaSeleccionada(true);
            }
        }
    }
    
    private void anadirBean(Pane target, int width) {
        var children  = target.getChildren();
        String imagenFrejol = "src/main/resources/imagenesNaipe/bean.png";
        var view = new ImageView(new Image(new File(imagenFrejol).toURI().toString()));

        view.setFitWidth(width);
        view.setPreserveRatio(true);

        Platform.runLater(() -> children.add(view));
    }

    private void showX(Pane target, int width) {
        var children = target.getChildren();
        String imagenX = "src/main/resources/imagenesNaipe/x.png";
        var view = new ImageView(new Image(new File(imagenX).toURI().toString()));

        view.setFitWidth(width);
        view.setPreserveRatio(true);

        Platform.runLater(() -> children.add(view));

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        Platform.runLater(() -> children.remove(view));
    }
    
    private void updateTablasOponentes() {
        paneOponentes.getChildren().clear();

        for (var op : oponentes) {
            CartaBlitter blitter;

            GridPane gridOponente = new GridPane();
            paneOponentes.getChildren().add(gridOponente);

            if (configuracion.getVisibilidadCarta()) {
                blitter = new StdBlitter(Configuracion.anchoCartaOponente);
            } else {
                blitter = new CartasNoVisiblesBlitter();
            }

            updateTabla(op.getTablero(), gridOponente, blitter);
        };
    }
    
    private void spawnThread(Runnable r, boolean daemon) {
        Thread t = new Thread(r);
        if (daemon) {
            t.setDaemon(true);
        }
        t.start();
    }
    
    private void unleashComputadora() {
        while (juegoActual.isRunning()) {
            oponentes
                .stream()
                .filter(op -> op.marcarCarta(juegoActual.getNaipeActual()))
                .forEach(op -> {
                    Platform.runLater(() -> updateTablasOponentes());
                    try 
                    { 
                        Thread.sleep(1000); 
                    } 
                    catch (Exception e) 
                    {
                        
                    }
                    try 
                    { 
                        if (help.HelperJuego.verificarJuegoGanado(op.getTablero(), alineacionGanadora)) {
                            Platform.runLater(() -> terminarJuego());}                                            
                    }
                    catch(Exception e)
                    {
                        
                    }
                });
        }
    }
    
    private void terminarJuego()
    {
        tableroActual = tableroHumano;
        int jugadorActual = 1;
        if(help.HelperJuego.verificarJuegoGanado(tableroActual,alineacionGanadora))
        {
            try
            {
                Jugador jugador = null;
                if(jugadorActual == 1)
                    jugador = jugadorHumano;
                else if(jugadorActual == 2)
                    jugador = jugadorPC1;
                else if(jugadorActual == 3) 
                    jugador = jugadorPC2;

                horaFin = new Date();
                help.HelperJuego.showMessage(new Alert(Alert.AlertType.INFORMATION),"Loteria.. ",null,"Usted ha ganado!");                                             
                long diferenciaTiempo = horaFin.getTime() - horaInicio.getTime();    
                int minutos = (int)diferenciaTiempo / (60 * 1000);
                Reporte reporte = new Reporte(horaFin,minutos,jugador.getNombre(),oponentes.size(),alineacionGanadora.name());
                Reporte.guardarReporte(reporte);
                Stage stageActual = (Stage) img_AlineacionGanadora.getScene().getWindow();            
                stageActual.close(); 
            }
            catch(Exception e)
            {
                
            }            
        }
        else        
            help.HelperJuego.showMessage(new Alert(Alert.AlertType.ERROR),"Error.. ",null,"Usted no ha ganado, favor continue con su juego!");                                                     
    }

    @FXML
    private void accionCambiarNaipe(ActionEvent event) {
    }

    @FXML
    private void accionVerificarLoteria(ActionEvent event) 
    {
        terminarJuego();
    }
}

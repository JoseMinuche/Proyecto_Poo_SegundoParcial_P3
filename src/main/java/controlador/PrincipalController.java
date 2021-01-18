package controlador;

import com.proyectoloteriamexicana.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {

    @FXML
    private Button btn_ComenzarJuego;
    @FXML
    private Button btn_VerTablaPuntaje;
    @FXML
    private Button btn_SalirJuego;
    @FXML
    private Button btn_SalirJuego1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void comenzarJuego(ActionEvent event) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Juego" + ".fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Juego Actual");
        stage.setMaxWidth(1050);
        stage.setMaxHeight(650);
        stage.setScene(new Scene(root));  
        stage.show();
    }

    @FXML
    private void accionConfiguracion(ActionEvent event) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Configuracion" + ".fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Panel de Configuración");
        stage.setMaxWidth(600);
        stage.setMaxHeight(375);
        stage.setScene(new Scene(root));  
        stage.show(); 
    }

    @FXML
    private void accionReporte(ActionEvent event) throws Exception
    {        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Reporte" + ".fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Panel de reportes");
        stage.setMaxWidth(600);
        stage.setMaxHeight(375);
        stage.setScene(new Scene(root));  
        stage.show();     
    }

    @FXML
    private void accionSalir(ActionEvent event) 
    {
        System.exit(0);
    }
    
}

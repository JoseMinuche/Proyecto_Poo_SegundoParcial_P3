package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Configuracion;

public class ConfiguracionController implements Initializable {

    @FXML
    private TextField txt_cantidad;
    @FXML
    private CheckBox chk_VisibilidadCartas;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actualizarConfiguracion(ActionEvent event) throws Exception
    {
        int cantidadCartas;
        boolean visibilidadCartas = chk_VisibilidadCartas.isSelected();
        try
        {
            cantidadCartas = Integer.parseInt(txt_cantidad.getText().trim());
        }
        catch(Exception e)
        {
            cantidadCartas = 0;
        }
        if(cantidadCartas <= 0 || cantidadCartas > 2)
            help.HelperJuego.showMessage(new Alert(Alert.AlertType.ERROR),"Validación",null,"Debe ingresar una cantidad máxima de 1 a 2 oponentes!");                                             
        else
        {
            Configuracion configuracion = new Configuracion(visibilidadCartas,cantidadCartas);
            Configuracion.guardarConfiguracion(configuracion);
            help.HelperJuego.showMessage(new Alert(Alert.AlertType.INFORMATION),"Configuración del Juego",null,"Se ha guardado correctamente la configuración del juego!");                                             
        }
    }

    @FXML
    private void accionRegresar(ActionEvent event) 
    {
        Stage stageActual = (Stage) txt_cantidad.getScene().getWindow();            
        stageActual.close();
    }
    
}

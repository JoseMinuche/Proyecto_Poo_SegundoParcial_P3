package controlador;

import help.HelperJuego;
import help.OrdenadorFecha;
import help.TablaReporte;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Reporte;

public class ReporteController implements Initializable {

    @FXML
    private TextField txt_FechaInicio;
    @FXML
    private TextField txt_FechaFin;
    @FXML
    private TableView<TablaReporte> tbl_Reportes;
    @FXML
    private Label lbl_lista_reportes;
    @FXML
    private Label lbl_SinReportes;
    
    ArrayList<Reporte> reportes;
    private ObservableList<TablaReporte> reportList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<TablaReporte,String> tbl_col_fecha;
    @FXML
    private TableColumn<TablaReporte,String> tbl_col_duracion;
    @FXML
    private TableColumn<TablaReporte,String> tbl_col_usuario;
    @FXML
    private TableColumn<TablaReporte,String> tbl_col_Oponente;
    @FXML
    private TableColumn<TablaReporte,String> tbl_col_Alineacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //cargar el archivo por defecto          
        cargarFormatoTabla();         
    }    

    @FXML
    private void consultarReportes(ActionEvent event) 
    {
        String fechaInicial = txt_FechaInicio.getText().trim();
        String fechaFinal = txt_FechaFin.getText().trim();
        if(fechaInicial.isEmpty() || fechaFinal.isEmpty())
            help.HelperJuego.showMessage(new Alert(Alert.AlertType.WARNING),"Consulta de reportes",null,"Debe registrar la fecha inicial y final!");
        else            
        {
            tbl_Reportes.getItems().clear();
            Date fechaIn = help.HelperJuego.StringToDateTime(fechaInicial);
            Date fechaFin = help.HelperJuego.StringToDateTime(fechaFinal);
            reportes = HelperJuego.consultarReportesFecha(fechaIn,fechaFin);
            reportes.sort(new OrdenadorFecha());
            String fecha,jugador,alineacion,duracion,oponente;
            for(Reporte reporte : reportes)
            {            
                fecha = help.HelperJuego.dateTimeToString(reporte.getFechaReporte());
                duracion = String.valueOf(reporte.getDuracionJuegoMinutos());
                jugador = reporte.getJugador();
                oponente = String.valueOf(reporte.getCantOponente());
                alineacion = reporte.getAlineacion();
                reportList.add(new TablaReporte(fecha,duracion,jugador,oponente, alineacion));
            }           
            tbl_Reportes.setItems(reportList);
        }
    }

    @FXML
    private void accionRegresar(ActionEvent event) throws IOException
    {
        Stage stageActual = (Stage) txt_FechaInicio.getScene().getWindow();            
        stageActual.close();
    }
    
    public void cargarReporteArchivo()
    {
        reportes = Reporte.desserializarReporte("Reporte.ser");        
        String fecha,jugador,alineacion,duracion,oponente;
        for(Reporte reporte : reportes)
        {            
            fecha = help.HelperJuego.dateTimeToString(reporte.getFechaReporte());
            duracion = String.valueOf(reporte.getDuracionJuegoMinutos());
            jugador = reporte.getJugador();
            oponente = String.valueOf(reporte.getCantOponente());
            alineacion = reporte.getAlineacion();
            reportList.add(new TablaReporte(fecha,duracion,jugador,oponente, alineacion));
        }
        tbl_Reportes.setItems(reportList);
    }
    
    public void cargarFormatoTabla()
    {
        cargarReporteArchivo();
        tbl_col_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));        
        tbl_col_duracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        tbl_col_usuario.setCellValueFactory(new PropertyValueFactory<>("jugador"));
        tbl_col_Oponente.setCellValueFactory(new PropertyValueFactory<>("oponentes"));
        tbl_col_Alineacion.setCellValueFactory(new PropertyValueFactory<>("alineacion"));        
        tbl_Reportes.setItems(reportList);
    }
}

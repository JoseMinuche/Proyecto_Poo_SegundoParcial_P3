
package help;

import javafx.scene.control.Alert;

public class HelperJuego 
{
    //Metodo que te permite mostrar una alerta en cualquier momento
     public static void showMessage(Alert show, String titulo, String encabezado, String mensaje){
        show.setHeaderText(encabezado);
        show.setTitle(titulo);
        show.setContentText(mensaje);
        show.showAndWait();
    }
    
}

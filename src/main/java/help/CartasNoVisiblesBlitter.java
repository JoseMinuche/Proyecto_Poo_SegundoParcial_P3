
package help;

import java.io.File;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Carta;
import modelo.Configuracion;

public class CartasNoVisiblesBlitter implements CartaBlitter {
    public Node prepararCarta(Carta[][] tabla, Carta carta) {        
        String rutaImagen = "src/main/resources/imagenesNaipe/back.png";                       
        Image image = new Image(new File(rutaImagen).toURI().toString());          
        
        //TODO
        //if (tabla.isMarcada(carta)) {
        //    rutaImagen = Configuracion.rutaMatch;
        //} else {
            //rutaImagen = Configuracion.rutaBack;
        //}

        ImageView view = new ImageView(image);

        view.setFitWidth(Configuracion.anchoCartaOponente);
        view.setPreserveRatio(true);

        return view;
    }
}

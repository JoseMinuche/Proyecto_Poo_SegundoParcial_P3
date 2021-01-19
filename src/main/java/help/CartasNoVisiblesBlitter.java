
package help;

import java.io.File;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.Carta;
import modelo.Configuracion;

public class CartasNoVisiblesBlitter implements CartaBlitter {
    @Override
    public Node prepararCarta(Carta[][] tabla, Carta carta) {        
        String rutaImagen = "src/main/resources/imagenesNaipe/back.png";                                       
        //TODO
        if (marcarCarta(tabla, carta)) 
        {
            System.out.println("Se encontro match en la pc="); 
           rutaImagen = "src/main/resources/imagenesNaipe/match.png";                           
        }
        Image image = new Image(new File(rutaImagen).toURI().toString());          
        ImageView view = new ImageView(image);

        view.setFitWidth(Configuracion.anchoCartaOponente);
        view.setPreserveRatio(true);

        return view;
    }
    
    public boolean marcarCarta(Carta[][] tablero, Carta cartaActual) 
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

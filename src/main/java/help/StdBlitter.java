package help;

import java.io.File;
import java.util.function.BiConsumer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import modelo.Carta;
import modelo.Configuracion;

public class StdBlitter implements CartaBlitter {
    private int anchoCarta;
    private BiConsumer<MouseEvent, Carta> onClickHandler;

    public StdBlitter(int anchoCarta, BiConsumer<MouseEvent, Carta> onClickHandler) {
        this.anchoCarta = anchoCarta;
        this.onClickHandler = onClickHandler;
    }

    public StdBlitter(int anchoCarta) {
        this.anchoCarta = anchoCarta;
    }       

    @Override
    public Node prepararCarta(Carta[][] tabla, Carta carta) 
    {
        StackPane st = new StackPane();
        String imagenCarta = "src/main/resources/imagenesNaipe/"+carta.getUrlImagenCarta();         
        Image image = new Image(new File(imagenCarta).toURI().toString());  
        ImageView view = new ImageView(image);
        view.setFitWidth(anchoCarta);
        view.setPreserveRatio(true);
       
        if (onClickHandler != null) {
            st.setOnMouseClicked(e -> onClickHandler.accept(e, carta));

            st.setOnMouseEntered(e -> {
                st.setTranslateY(2);
                st.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,250,0.8), 10, 0, 0, 0)");
            });

            st.setOnMouseExited(e -> {
                st.setTranslateY(0);
                st.setStyle("-fx-effect: none");
            });
            System.out.println("Se setean los listener");
        }                
        st.getChildren().add(view); 
        if(carta.getCartaSeleccionada())
        {
            String imagenFrejol = "src/main/resources/imagenesNaipe/bean.png";            
            ImageView bean  = new ImageView(new Image(new File(imagenFrejol).toURI().toString()));
            bean.setFitWidth(anchoCarta  / 2);
            bean.setPreserveRatio(true);
            st.getChildren().add(bean);
        }
        return st;
    }
    
    public boolean marcarCarta(Carta[][] tablero, Carta cartaActual) 
    {
        for(int i = 0; i < 4 ; i++)
        {
            for(int j = 0; j < 4; j++)
            {                
                var carta = tablero[i][j];
                System.out.println("carta Tablero=>"+carta.getNombre()+" - "+carta.getNumero());
                System.out.println("carta actual=>"+cartaActual.getNombre()+" - "+cartaActual.getNumero());
                if(carta.getNumero() == cartaActual.getNumero() && !carta.getCartaSeleccionada())            
                {                                                                                                                                                                      
                    System.out.println("Marcar carta  carta Tablero=>"+carta.getNombre()+" - "+carta.getNumero());
                    return true;
                }
                    
            }            
        }               
        return false;                
    }
}
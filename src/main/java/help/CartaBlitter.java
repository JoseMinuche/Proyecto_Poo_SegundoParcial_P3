package help;

import javafx.scene.Node;
import modelo.Carta;

public interface CartaBlitter {
    Node prepararCarta(Carta[][] mazo, Carta carta);
}
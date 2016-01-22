package pieces;

import javafx.beans.property.IntegerProperty;
import javafx.scene.image.ImageView;

import java.util.Collection;

/**
 * Created by antant on 21/01/16.
 */
public class Pawn extends Piece{
    public Pawn(boolean white) {
        super(white);
        setIcon();
    }

    public String toString(){
        return "p";
    }

    @Override
    public void setIcon() {
        if (isWhite())
            setStyle("-fx-background-image: url('/images/wpawn');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        else
            setStyle("-fx-background-image: url('/images/bpawn');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }





}

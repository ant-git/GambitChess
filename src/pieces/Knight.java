package pieces;

import javafx.scene.paint.Color;

/**
 * Created by antant on 21/01/16.
 */
public class Knight extends Piece {


    public Knight(int x, int y, Color color) {
        super(x, y, color);
        setIcon();
    }

    public String toString(){
        return "k";
    }

    @Override
    public void setIcon() {
        if(getColor().equals(Color.WHITE))
            setStyle("-fx-background-image: url('/images/wknight');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        if(getColor().equals(Color.BLACK))
            setStyle("-fx-background-image: url('/images/bknight');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }
}

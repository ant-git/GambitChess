package pieces;

import javafx.scene.paint.Color;

/**
 * Created by antant on 21/01/16.
 */
public class King extends Piece {


    public King(int x, int y, Color color) {
        super(x, y, color);
        setIcon();
    }

    public String toString(){
        return "K";
    }

    @Override
    public void setIcon() {
        if(getColor().equals(Color.WHITE))
            setStyle("-fx-background-image: url('/images/wking');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        if(getColor().equals(Color.BLACK))
            setStyle("-fx-background-image: url('/images/bking');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }

    public void highlight(){
        if(getColor().equals(Color.WHITE))
            setStyle("-fx-background-image: url('/images/wking');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat;" +
                    "");  // ** thats how to add image
        else
            setStyle("-fx-background-image: url('/images/bking');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");
    }

}

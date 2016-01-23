package pieces;

import javafx.scene.paint.Color;

/**
 * Created by antant on 21/01/16.
 */
public class Bishop extends Piece {


    public Bishop(int x, int y, Color color) {
        super(color);
        setDefaultIcon();
    }

    public String toString(){
        return "b";
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setDefaultIcon() {
        if(getColor().equals(Color.WHITE))
            setStyle("-fx-background-image: url('/images/wbishop');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image

        if(getColor().equals(Color.BLACK))
            setStyle("-fx-background-image: url('/images/bbishop');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }

    @Override
    public void dehighlight() {

    }
}

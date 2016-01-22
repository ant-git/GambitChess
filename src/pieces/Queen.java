package pieces;

/**
 * Created by antant on 21/01/16.
 */
public class Queen extends Piece {

    public Queen(boolean white) {
        super(white);
        setIcon();
    }

    public String toString(){
        return "Q";
    }
    @Override
    public void setIcon() {
        if(isWhite())
            setStyle("-fx-background-image: url('/images/wqueen');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        else
            setStyle("-fx-background-image: url('/images/bqueen');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }
}

package pieces;

/**
 * Created by antant on 21/01/16.
 */
public class Rook extends Piece {


    public Rook(boolean white) {
        super(white);
        setIcon();
    }

    public String toString(){
        return "r";
    }

    @Override
    public void setIcon() {
        if(isWhite())
            setStyle("-fx-background-image: url('/images/wrook');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        else
            setStyle("-fx-background-image: url('/images/brook');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }
}

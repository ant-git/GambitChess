package pieces;

/**
 * Created by antant on 21/01/16.
 */
public class Knight extends Piece {


    public Knight(boolean white) {
        super(white);
        setIcon();
    }

    public String toString(){
        return "k";
    }

    @Override
    public void setIcon() {
        if(isWhite())
            setStyle("-fx-background-image: url('/images/wknight');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        else
            setStyle("-fx-background-image: url('/images/bknight');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }
}

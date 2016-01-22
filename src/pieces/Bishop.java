package pieces;

/**
 * Created by antant on 21/01/16.
 */
public class Bishop extends Piece {


    public Bishop(boolean white) {
        super(white);
        setIcon();
    }

    public String toString(){
        return "b";
    }

    @Override
    public void setIcon() {
        if(isWhite())
            setStyle("-fx-background-image: url('/images/wbishop');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        else
            setStyle("-fx-background-image: url('/images/bbishop');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }
}

package pieces;

/**
 * Created by antant on 21/01/16.
 */
public class King extends Piece {


    public King(boolean white) {
        super(white);
        setIcon();
    }

    public String toString(){
        return "K";
    }

    @Override
    public void setIcon() {
        if(isWhite())
            setStyle("-fx-background-image: url('/images/wking');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        else
            setStyle("-fx-background-image: url('/images/bking');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }

    public void highlight(){
        if(isWhite())
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

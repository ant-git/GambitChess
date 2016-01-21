package pieces;

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
        if(isWhite()){
            System.out.println(isWhite());
            this.setStyle("-fx-background-color: blue");
        }
    }

    public void remove(){
        this.getChildren().remove(Pawn.this);
    }


}

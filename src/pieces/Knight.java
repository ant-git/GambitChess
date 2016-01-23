package pieces;

import game.ChessBoard;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public class Knight extends Piece {


    public Knight(int x, int y, Color color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
        setDefaultIcon();
    }

    public String toString(){
        return "k";
    }

    @Override
    public void setDefaultIcon() {
        if(getColor().equals(Color.WHITE))
            setStyle("-fx-background-image: url('/images/wknight');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        if(getColor().equals(Color.BLACK))
            setStyle("-fx-background-image: url('/images/bknight');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }

    @Override
    public void setHighlightedIcon() {

    }

    @Override
    public void setPickIcon() {

    }

    @Override
    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        int x = getX();
        int y = getY();

        //x-1 y-2
        //x+1 y-2
        //x-2 y-1
        //x+2 y-1
        //x-2 y+1
        //x-1 y+2
        //x+1 y+2
        //x+2 y+1


        return moves;
    }


}

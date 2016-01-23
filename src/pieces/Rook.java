package pieces;

import game.ChessBoard;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public class Rook extends Piece {


    public Rook(int x, int y, Color color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
        setDefaultIcon();
    }

    public String toString(){
        return "r";
    }


    @Override
    public void setDefaultIcon() {
        if(getColor().equals(Color.WHITE))
            setStyle("-fx-background-image: url('/images/wrook');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        if(getColor().equals(Color.BLACK))
            setStyle("-fx-background-image: url('/images/brook');" +
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
        return null;
    }
}

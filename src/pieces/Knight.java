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
        boolean white = pieceIsWhiteAtIndex(x,y);

        //x-1 y-2
        if((x - 1 >=0 && y - 2 >= 0) && (getBoard().getSquare(x-1,y-2).isEmpty() || pieceIsWhiteAtIndex(x-1 , y-2) != white))
            moves.add(new Move(x, y, x - 1, y - 2));
        //x+1 y-2
        if((x + 1 <= 7 && y - 2 >= 0) && (getBoard().getSquare(x+1,y-2).isEmpty() || pieceIsWhiteAtIndex(x+1 , y-2) != white))
            moves.add(new Move(x, y, x + 1, y - 2));
        //x-2 y-1
        if((x - 2 >=0 && y - 1 >= 0) && (getBoard().getSquare(x-2,y-1).isEmpty() || pieceIsWhiteAtIndex(x-2 , y-1) != white))
            moves.add(new Move(x, y, x - 2, y - 1));
        //x+2 y-1
        if((x + 2 <= 7 && y - 1 >= 0) && (getBoard().getSquare(x+2,y-1).isEmpty() || pieceIsWhiteAtIndex(x+2 , y-1) != white))
            moves.add(new Move(x, y, x + 2, y - 1));
        //x-2 y+1
        if((x - 2 >=0 && y + 1 <= 7) && (getBoard().getSquare(x-2,y+1).isEmpty() || pieceIsWhiteAtIndex(x-2 , y+1) != white))
            moves.add(new Move(x, y, x - 2, y + 1));
        //x-1 y+2
        if((x - 1 >=0 && y + 2 <= 7) && (getBoard().getSquare(x-1,y+2).isEmpty() || pieceIsWhiteAtIndex(x-1 , y+2) != white))
            moves.add(new Move(x, y, x - 1, y + 2));
        //x+1 y+2
        if((x + 1 <= 7 && y + 2 <= 7) && (getBoard().getSquare(x+1,y+2).isEmpty() || pieceIsWhiteAtIndex(x+1 , y+2) != white))
            moves.add(new Move(x, y, x + 1, y + 2));
        //x+2 y+1
        if((x + 2 <= 7 && y + 1 <= 7) && (getBoard().getSquare(x+2,y+1).isEmpty() || pieceIsWhiteAtIndex(x+2 , y+1) != white))
            moves.add(new Move(x, y, x + 2, y + 1));

        return moves;
    }


}

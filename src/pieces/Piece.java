package pieces;

import game.ChessSquare;

/**
 * Created by antant on 21/01/16.
 */
public class Piece {
    private ChessSquare chessSquare;
    private String color;

    public Piece() {

    }

    public String toString() {
        return "R";
    }

    public ChessSquare getSquare(){
        return chessSquare;
    }
    public void setChessSquare(ChessSquare chessSquare){
        this.chessSquare = chessSquare;
    }

    public int getX(){
        return chessSquare.getX();
    }

    public int getY(){
        return chessSquare.getY();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

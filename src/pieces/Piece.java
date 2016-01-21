package pieces;

import game.ChessSquare;

import javax.swing.*;

/**
 * Created by antant on 21/01/16.
 */
public abstract class Piece {
    private ChessSquare chessSquare;
    private String color;

    public Piece(String color) {
        this.color = color;
    }

    public String toString() {
        return "P";
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

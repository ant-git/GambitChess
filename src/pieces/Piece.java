package pieces;

import game.ChessSquare;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.swing.*;

/**
 * Created by antant on 21/01/16.
 */
public abstract class Piece extends Pane{
    private ChessSquare chessSquare;
    private String color;
    private boolean white;
    private int x;
    private int y;


    public Piece(boolean white) {
        this.white = white;
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
        return x;
    }

    public int getY(){
        return y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isWhite(){
        return white;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIcon(){
    }
}

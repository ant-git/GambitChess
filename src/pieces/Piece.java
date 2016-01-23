package pieces;

import game.ChessSquare;
import game.Move;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.*;

/**
 * Created by antant on 21/01/16.
 */
public abstract class Piece extends Pane{
    private ChessSquare chessSquare;
    private Color color = Color.WHITE;
    private int x;
    private int y;


    public Piece(int x, int y, Color color) {
        this.color = color;
        this.x = x;
        this.y = y;
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

    public Color getColor() {
        return color;
    }

    public void setIcon(){
    }

    public void highlight(Move move){

    }

    public void setDefaultListener(){

    }

    public abstract void dehighlight();
    public boolean isHighlighted(){
        return false;
    }

    public void removeListeners(){

    }
}

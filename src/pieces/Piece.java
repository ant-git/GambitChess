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



    public Piece(Color color) {
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

    public abstract int getX();

    public abstract int getY();


    public Color getColor() {
        return color;
    }

    public void setDefaultIcon(){
    }

    public void setHighlightedIcon(){
    }

    public void setPickIcon(){
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

    public void move(int x, int y){

    }

    public void kill(Piece killer){

    }
    public void setDeselectListener(){

    }
    public void setUnderTreatListener(Piece killer){

    }
}

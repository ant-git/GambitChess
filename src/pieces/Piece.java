package pieces;

import game.ChessBoard;
import game.ChessSquare;
import game.Move;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public abstract class Piece extends Pane{
    private ChessSquare chessSquare;
    private Color color = Color.WHITE;
    private int x;
    private int y;
    private ChessBoard board;
    private boolean highlighted;

    public Piece(int x, int y, Color color, ChessBoard board) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.board = board;
        highlighted = false;

    }

    public String toString() {
        return "P";
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

    public void setDefaultIcon(){
    }

    public void setHighlightedIcon(){
    }

    public void setPickIcon(){
    }

    public ChessBoard getBoard() {
        return board;
    }


    public void dehighlight() {
        setDefaultIcon();
        highlighted = false;
    }

    public void setUnderTreatListener(Piece killer){
        System.out.println("UNDER TREAT");
        setOnMouseClicked(event -> {
            getKilled(killer);
            board.setDefaultListeners();
        });
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        setHighlightedIcon();
        this.highlighted = highlighted;
    }

    public void move(int newX, int newY){
        board.getSquare(this).removePiece();
        board.getChessBoard().getChildren().remove(this);
        board.getChessBoard().add(this, newX, newY);
        setX(newX);
        setY(newY);

        board.getSquare(newX,newY).setPiece(this);
        highlighted = false;
        System.out.println("Move successful! Alive pieces left: " + getBoard().getAlivePiecesCount());
    }


    public void getKilled(Piece killer){
        board.getChessBoard().getChildren().remove(this);
        board.getSquare(this).removePiece();
        killer.move(x, y);
    }

    public void highlight(Move move) {
        if(move.getNewX() == getX() && move.getNewY() == getY()){
            setHighlightedIcon();
            highlighted = true;
        }
    }

    public void setDefaultListener(){
        setOnMouseClicked(event -> {
            pick();
            System.out.println("Color: " + getColor().toString());
        });
    }

    public void setDeselectListener(){
        setOnMouseClicked(event -> {
            getBoard().setDefaultListeners();
        });
    }

    public void removeListeners(){
        setOnMouseClicked(event -> {});
    }


    public abstract ArrayList<Move> getAvailableMoves();

    public void pick(){
        setPickIcon();
        getBoard().highlightPossibleMoves(getAvailableMoves());
        setOnMouseClicked(event -> {
            getBoard().setDefaultListeners();
        });
        getBoard().setHighlightOnlyListeners(this);
    }


}

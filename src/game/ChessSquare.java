package game;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pieces.Piece;

/**
 * Created by antant on 21/01/16.
 */
public class ChessSquare extends Pane{
    int x;
    int y;
    Piece piece;
    boolean empty;
    Color color;

    public ChessSquare(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        setColor();
        empty = true;

    }

    private void setColor(){
        if(color.equals(Color.WHITE))
            this.setStyle("-fx-background-color: wheat");
        if(color.equals(Color.BLACK))
            this.setStyle("-fx-background-color: saddlebrown");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        piece.setChessSquare(this);
        empty = false;
    }
    public void removePiece(){
        piece = null;
        empty = true;
    }

    public boolean isEmpty(){
        return empty;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

}

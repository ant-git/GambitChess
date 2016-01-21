package game;

import pieces.Piece;

/**
 * Created by antant on 21/01/16.
 */
public class ChessSquare {
    int x;
    int y;
    Piece piece;
    String color;
    boolean empty;

    public ChessSquare(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        empty = true;
    }

    public String displayString(){
        return color;
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

    public String getColor() {
        String c;
        if(!empty){
            c = piece.toString();

        }
        else {
            c = color;
        }
        return c;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

package game;

import javafx.scene.layout.Pane;
import pieces.Piece;

/**
 * Created by antant on 21/01/16.
 */
public class ChessSquare extends Pane{
    int x;
    int y;
    Piece piece;
    boolean empty;
    boolean white;

    public ChessSquare(int x, int y, boolean white) {
        this.x = x;
        this.y = y;
        this.white = white;
        if(white)
            this.setStyle("-fx-background-color: wheat");
        else
            this.setStyle("-fx-background-color: saddlebrown");
        empty = true;
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

    public boolean isWhite() {
        return white;
    }

}

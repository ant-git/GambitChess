package game;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pieces.Piece;

/**
 * Created by antant on 21/01/16.
 */
public class ChessSquare extends Pane implements Highlightable{
    int x;
    int y;
    Piece piece;
    boolean empty;
    Color color;
    private boolean highlighted;
    String blackSquareStyle = "-fx-background-color: saddlebrown";
    String whiteSquareStyle = "-fx-background-color: wheat";

    String blackSquareHStyle = "-fx-background-color: saddlebrown; " +
                               "-fx-background-image: url('/images/circle');" +
                               "-fx-background-position: center center;" +
                               "-fx-background-repeat: no-repeat;";

    String whiteSquareHStyle = "-fx-background-color: wheat; " +
                               "-fx-background-image: url('/images/circle');" +
                               "-fx-background-position: center center;" +
                               "-fx-background-repeat: no-repeat;";

    ChessBoard board;

    public ChessSquare(int x, int y, Color color, ChessBoard board) {
        this.x = x;
        this.y = y;
        this.color = color;
        setColor();
        empty = true;
        highlighted = false;
        this.board = board;

    }

    private void setColor(){
        if(color.equals(Color.WHITE))
            this.setStyle(whiteSquareStyle);
        if(color.equals(Color.BLACK))
            this.setStyle(blackSquareStyle);
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

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }


    @Override
    public void highlight(Move move){
        if(move.getNewX() == x && move.getNewY() == y && isEmpty()){
            System.out.println("HIGHLIGHTED! @ " + x + " " + y);
            if(color.equals(Color.WHITE))
                this.setStyle(whiteSquareHStyle);
            if(color.equals(Color.BLACK))
                this.setStyle(blackSquareHStyle);
            highlighted = true;
        }
    }

    @Override
    public void dehighlight() {
        setColor();
        highlighted = false;
    }

    public void setDefaultListener(){
        this.setOnMouseClicked(event -> {
            System.out.println("SQUARE CLICKED @ " + x + " " +y);
        });
    }

    public void removeListeners(){
        setOnMouseClicked(event -> {});
    }

}

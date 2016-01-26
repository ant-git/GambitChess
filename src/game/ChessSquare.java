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

    Game board;

    public ChessSquare(int x, int y, Color color, Game board) {
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

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        //piece.setChessSquare(this);
        empty = false;
    }
    public void removePiece(){
        piece = null;
        empty = true;
    }

    public boolean isEmpty(){
        return empty;
    }


    public boolean isHighlighted() {
        return highlighted;
    }


    @Override
    public void highlight(Move move){
        if(move.getNewX() == x && move.getNewY() == y && isEmpty()){
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

        });
    }

    public void removeListeners(){
        setOnMouseClicked(event -> {
        });
    }

    public void receivePiece(Piece piece){
        setOnMouseClicked(event -> {
            piece.move(x, y);
            setPiece(piece);
            board.dehighlightAllMoves();
        });

    }
}

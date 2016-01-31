package game;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pieces.King;
import pieces.Pawn;
import pieces.Piece;

/**
 * Created by Anton
 * Class that represents Chess Square
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

    Game game;

    public ChessSquare(int x, int y, Color color, Game game) {
        this.x = x;
        this.y = y;
        this.color = color;
        setColor();
        empty = true;
        highlighted = false;
        this.game = game;

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
        game.getChessBoard().getChildren().remove(piece);
        this.piece = piece;
        empty = false;
        piece.setX(x);
        piece.setY(y);
        game.getChessBoard().add(piece, piece.getX(), piece.getY());
    }
    public void removePiece(){
        piece = null;
        empty = true;
        game.getChessBoard().getChildren().remove(piece);
    }

    public boolean isEmpty(){
        return empty;
    }


    public boolean isHighlighted() {
        return highlighted;
    }


    //method to highlight Chess Square
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

    //to set on mouse click listener, after piece is selected
    public void receivePieceListener(Piece piece){
        setOnMouseClicked(event -> {
            setCastling(piece);
            setEnPassant(piece);
            game.dehighlightAllMoves();
            piece.move(x, y);
        });

    }

    //to check if king is suitable for castling
    public void setCastling(Piece piece){
        if(x == 2 && y == 0 && piece instanceof King && piece.getMoveCount() == 0)
            game.getSquare(0,0).getPiece().move(3,0);

        if(x == 6 && y == 0 && piece instanceof King && piece.getMoveCount() == 0)
            game.getSquare(7,0).getPiece().move(5,0);

        if(x == 2 && y == 7 && piece instanceof King && piece.getMoveCount() == 0)
            game.getSquare(0,7).getPiece().move(3,7);

        if(x == 6 && y == 7 && piece instanceof King && piece.getMoveCount() == 0)
            game.getSquare(7,7).getPiece().move(5,7);


    }
    //to check if piece is suitable for en passant move
    public void setEnPassant(Piece piece){
        if(piece instanceof Pawn && ((Pawn) piece).isEnPassant()) {
            game.dehighlightAllMoves();
            if(y == 5){
                game.addToCapturedList(game.getPiece(x, y-1));
                game.getPiece(x, y-1).remove();
                ((Pawn) piece).setEnPassant(false);
            }
            if(y == 2){
                game.addToCapturedList(game.getPiece(x, y+1));
                game.getPiece(x, y+1).remove();
                ((Pawn) piece).setEnPassant(false);
            }

        }
    }


}

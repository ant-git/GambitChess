package pieces;

import game.ChessBoard;
import game.ChessSquare;
import game.Move;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public abstract class Piece extends Pane{
    private Color color;
    private int x;
    private int y;
    private ChessBoard board;
    private boolean highlighted;
    private ChessSquare square;

    public Piece(int x, int y, Color color, ChessBoard board) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.board = board;
        highlighted = false;
        square = board.getSquare(this);
        square.setPiece(this);
        setDefaultIcon();

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

    public void setIcon(String whiteIcon, String blackIcon){
        if (getColor().equals(Color.WHITE))
            setStyle(whiteIcon);  // ** thats how to add image
        if(getColor().equals(Color.BLACK))
            setStyle(blackIcon);
    }

    public abstract void setDefaultIcon();

    public abstract void setHighlightedIcon();

    public abstract void setPickIcon();

    public ChessBoard getBoard() {
        return board;
    }


    public void dehighlight() {
        setDefaultIcon();
        highlighted = false;
    }

    public void setUnderTreatListener(Piece killer){
        setOnMouseClicked(event -> {
            getKilled(killer);
            board.dehighlightAllMoves();
        });
    }


    public boolean pieceIsWhiteAtIndex(int x, int y){
        return board.getSquare(x,y).getPiece().getColor().equals(Color.WHITE);
    }

    public boolean isHighlighted() {
        return highlighted;
    }


    public void move(int newX, int newY){
        System.out.println("JUST MOVE");
        board.getSquare(this).removePiece();
        board.getChessBoard().getChildren().remove(this);
        board.getChessBoard().add(this, newX, newY);
        setX(newX);
        setY(newY);
        board.getSquare(newX,newY).setPiece(this);
        highlighted = false;
        board.dehighlightAllMoves();

        if(getColor().equals(Color.WHITE))
            board.setListenersFor(Color.BLACK);

        else
            board.setListenersFor(Color.WHITE);



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
        });
    }

    public void setDeselectListener(){
        setOnMouseClicked(event -> {
            board.dehighlightAllMoves();
            board.setListenersFor(getColor());
        });
    }

    public void removeListeners(){
        setOnMouseClicked(event -> {});
    }

    public boolean isMoveSafe(Move move){
        boolean check = false;
        board.getSquare(this).removePiece();
        board.getChessBoard().getChildren().remove(this);
        //piece removed from square and board
        if(!board.getSquare(move.getNewX(), move.getNewY()).isEmpty()){
            Piece piece = board.getSquare(move.getNewX(), move.getNewY()).getPiece();
            board.getSquare(piece).removePiece();
            board.getChessBoard().getChildren().remove(piece);
            //possible enemy piece removed from square and board

            board.getChessBoard().add(this, move.getNewX(), move.getNewY());
            //piece added to square and board
            setX(move.getNewX());
            setY(move.getNewY());
            board.getSquare(move.getNewX(), move.getNewY()).setPiece(this);


            if(isKingUnderTreat(color))
                check = true;

            board.getSquare(this).removePiece();
            board.getChessBoard().getChildren().remove(this);
            //piece removed from square and board

            board.getChessBoard().add(this, move.getX(), move.getY());
            //piece added to square and board
            setX(move.getX());
            setY(move.getY());
            board.getSquare(move.getX(), move.getY()).setPiece(this);


            board.getChessBoard().add(piece, move.getNewX(), move.getNewY());
            board.getSquare(move.getNewX(), move.getNewY()).setPiece(piece);
            //enemy piece came back to board

        }
        else{
            board.getChessBoard().add(this, move.getNewX(), move.getNewY());
            setX(move.getNewX());
            setY(move.getNewY());
            board.getSquare(move.getNewX(), move.getNewY()).setPiece(this);
            if(isKingUnderTreat(color))
                check = true;
            board.getSquare(this).removePiece();
            board.getChessBoard().getChildren().remove(this);

            board.getChessBoard().add(this, move.getX(), move.getY());
            setX(move.getX());
            setY(move.getY());
            board.getSquare(move.getX(), move.getY()).setPiece(this);

        }

        return !check;
    }

    public ArrayList<Move> getAllSafeMoves(){
        ArrayList<Move> movesWithoutCheck = new ArrayList<>();
        for (Move move : getAvailableMoves())
        {
            if(isMoveSafe(move))
                movesWithoutCheck.add(move);
        }

        return movesWithoutCheck;
    }


    public abstract ArrayList<Move> getAvailableMoves();

    public void pick() {
        setPickIcon();
        board.highlightPossibleMoves(getAllSafeMoves());
        setDeselectListener();
        board.setHighlightOnlyListeners(this);

    }

    public ArrayList<Move> getDiagonalMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        boolean white = pieceIsWhiteAtIndex(x,y);

        int i = 1;
        while(x-i >=0 && y-i >= 0){
            if(getBoard().getSquare(x-i,y-i).isEmpty()){
                moves.add(new Move(x, y, x - i, y - i));
            }else{
                if(pieceIsWhiteAtIndex(x-i, y-i) != white){
                    moves.add(new Move(x, y, x - i, y - i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x+i <= 7 && y-i >= 0){
            if(getBoard().getSquare(x+i,y-i).isEmpty()){
                moves.add(new Move(x, y, x + i, y - i));
            }else{
                if(pieceIsWhiteAtIndex(x+i, y-i) != white){
                    moves.add(new Move(x, y, x + i, y - i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x-i >= 0 && y+i <= 7){
            if(getBoard().getSquare(x-i,y+i).isEmpty()){
                moves.add(new Move(x, y, x - i, y + i));
            }else{
                if(pieceIsWhiteAtIndex(x-i, y+i) != white){
                    moves.add(new Move(x, y, x - i, y + i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x+i <= 7 && y+i <= 7){
            if(getBoard().getSquare(x+i,y+i).isEmpty()){
                moves.add(new Move(x, y, x + i, y + i));
            }else{
                if(pieceIsWhiteAtIndex(x+i, y+i) != white){
                    moves.add(new Move(x, y, x + i, y + i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }


        return moves;
    }

    public ArrayList<Move> getAxisMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        boolean white = pieceIsWhiteAtIndex(x,y);

        int i = 1;
        while(y-i >= 0){
            if(getBoard().getSquare(x,y-i).isEmpty()){
                moves.add(new Move(x, y, x, y - i));
            }else{
                if(pieceIsWhiteAtIndex(x, y-i) != white){
                    moves.add(new Move(x, y, x, y - i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x-i >= 0){
            if(getBoard().getSquare(x-i,y).isEmpty()){
                moves.add(new Move(x, y, x-i, y));
            }else{
                if(pieceIsWhiteAtIndex(x-i, y) != white){
                    moves.add(new Move(x, y, x - i, y));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x+i <= 7){
            if(getBoard().getSquare(x+i,y).isEmpty()){
                moves.add(new Move(x, y, x+i, y));
            }else{
                if(pieceIsWhiteAtIndex(x+i, y) != white){
                    moves.add(new Move(x, y, x+i, y));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(y+i <= 7){
            if(getBoard().getSquare(x,y+i).isEmpty()){
                moves.add(new Move(x, y, x, y + i));
            }else{
                if(pieceIsWhiteAtIndex(x, y+i) != white){
                    moves.add(new Move(x, y, x, y + i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        return moves;
    }

    public String generateIcon(String url){
        return "-fx-background-image: url('" + url + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: no-repeat";
    }

    public boolean isWhite(){
        if(color.equals(Color.WHITE))
            return true;
        else
            return false;
    }
    public Color getEnemyColor(){
        if(isWhite())
            return Color.BLACK;
        else
            return Color.WHITE;
    }

    public boolean isKingUnderTreat(Color color){

        boolean underTreat = false;
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(board.getPossibleMovesForAll(getEnemyColor()));
        for(Move move : moves){
            if(move.getNewX() == board.getKing(color).getX() && move.getNewY() == board.getKing(color).getY()){
                underTreat = true;
            }
        }

        return underTreat;
    }
}

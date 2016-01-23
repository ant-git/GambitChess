package pieces;

import game.ChessBoard;
import game.ChessSquare;
import game.Highlightable;
import game.Move;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by antant on 21/01/16.
 */
public class Pawn extends Piece implements Highlightable{
    private ChessBoard board;
    private final int maxMoves = 4;
    private int x;
    private int y;
    private Color color;
    private ChessSquare square;
    private boolean highlighted;
    private boolean picked;

    private String blackPawnStyle = "-fx-background-image: url('/images/bpawn');" +
                                    "-fx-background-position: center center;" +
                                    "-fx-background-repeat: no-repeat;";

    private String whitePawnStyle = "-fx-background-image: url('/images/wpawn');" +
                                    "-fx-background-position: center center;" +
                                    "-fx-background-repeat: no-repeat";

    public Pawn(int x, int y, Color color, ChessBoard chessBoard) {
        super(x,y,color);
        this.board = chessBoard;
        this.x = this.getX();
        this.y = this.getY();
        this.color = this.getColor();
        square = board.getSquare(this);
        square.setPiece(this);
        highlighted = false;
        picked = false;
        setIcon();
    }

    public String toString(){
        return "p";
    }

    @Override
    public void setIcon() {
        if (color.equals(Color.WHITE))
            setStyle(whitePawnStyle);  // ** thats how to add image
        if(color.equals(Color.BLACK))
            setStyle(blackPawnStyle);

    }

    public void pick(){
        picked = true;
        board.highlightPossibleMoves(getAvailableMoves());
        setOnMouseClicked(event -> {
            board.setDefaultListeners();
        });
        board.setHighlightOnlyListeners(this);
    }

    private ArrayList<Move> getAvailableMoves(){
        ArrayList<Move> moves = new ArrayList<>();

        if(color.equals(Color.WHITE)) {
            if(board.getSquare(x, y-1).isEmpty()){
                moves.add(new Move(x, y, x, y - 1));
            }
            if(y==6 && board.getSquare(x,y-2).isEmpty()){
                moves.add(new Move(x, y, x, y - 2));
            }
            if((y-1 >= 0 && x-1 >= 0) && !board.getSquare(x-1,y-1).isEmpty() && board.getSquare(x-1,y-1).getPiece().getColor().equals(Color.BLACK)){
                System.out.println("OMFG ITS WORKING");
                moves.add(new Move(x, y, x - 1, y - 1));
            }
            if((y-1 >= 0 && x+1 <= 7) && !board.getSquare(x+1,y-1).isEmpty() && board.getSquare(x+1,y-1).getPiece().getColor().equals(Color.BLACK)) {
                System.out.println("OMFG ITS WORKING");
                moves.add(new Move(x, y, x + 1, y - 1));
            }
        }
        if(color.equals(Color.BLACK)) {
            System.out.println("SQUARE IS " + !board.getSquare(x-1,y+1).isEmpty());
            if(y+1 <= 7 && board.getSquare(x, y+1).isEmpty()){
                moves.add(new Move(x, y, x, y + 1));
            }
            if(y==1 && board.getSquare(x,y+2).isEmpty()){
                moves.add(new Move(x, y, x, y + 2));
            }

            if((y+1 <= 7 && x-1 >= 0) && !board.getSquare(x-1,y+1).isEmpty() && board.getSquare(x-1,y+1).getPiece().getColor().equals(Color.WHITE)){
                System.out.println("OMFG ITS WORKING");
                moves.add(new Move(x, y, x - 1, y + 1));
            }
            if((y+1 <= 7 && x+1 <= 7) && !board.getSquare(x+1,y+1).isEmpty() && board.getSquare(x+1,y+1).getPiece().getColor().equals(Color.WHITE)) {
                System.out.println("OMFG ITS WORKING");
                moves.add(new Move(x, y, x + 1, y + 1));
            }
        }
        System.out.println();
        System.out.println(moves.toString());
        return moves;
    }

    private boolean moveCheck(Move move){
        boolean movePossible = true;

        if(color.equals(Color.WHITE)){

        }

        return movePossible;
    }


    public void move(int newX, int newY){
        board.getSquare(this).removePiece();
        board.getChessBoard().getChildren().remove(this);
        board.getChessBoard().add(this, newX, newY);
        x = newX;
        y = newY;
        highlighted = false;
        System.out.println("Move successful! Alive pieces left: " + board.getAlivePiecesCount());
    }


    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public void highlight(Move move) {
        if(move.getNewX() == x && move.getNewY() == y){
            highlighted = true;
        }
    }

    @Override
    public void dehighlight() {
        setIcon();
        highlighted = false;
    }

    public void setDefaultListener(){
        setOnMouseClicked(event -> {
            pick();
        });
    }

    public void setDeselectListener(){
        setOnMouseClicked(event -> {
            board.setDefaultListeners();
        });
    }

    public void removeListeners(){
        setOnMouseClicked(event -> {});
    }

    public void setUnderTreatListener(Piece killer){
        System.out.println("UNDER TREAT");
        setOnMouseClicked(event -> {
            getKilled(killer);
            board.setDefaultListeners();
        });
    }

    public void getKilled(Piece killer){
        board.getChessBoard().getChildren().remove(this);
        board.getSquare(this).removePiece();
        System.out.println("DEAD");
        killer.move(x, y);
    }

}

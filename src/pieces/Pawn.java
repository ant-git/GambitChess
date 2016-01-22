package pieces;

import game.ChessBoard;
import game.ChessSquare;
import game.Move;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by antant on 21/01/16.
 */
public class Pawn extends Piece{
    private ChessBoard board;
    private final int maxMoves = 4;
    private int x;
    private int y;
    private Color color;
    private ChessSquare square;

    public Pawn(int x, int y, Color color, ChessBoard chessBoard) {
        super(x,y,color);
        this.board = chessBoard;
        this.x = this.getX();
        this.y = this.getY();
        this.color = this.getColor();
        square = board.getSquare(this);
        square.setPiece(this);
        setIcon();

        setOnMouseClicked(event -> {
            System.out.println("Pawn picked!");
        });
    }

    public String toString(){
        return "p";
    }

    @Override
    public void setIcon() {
        if (color.equals(Color.WHITE))
            setStyle("-fx-background-image: url('/images/wpawn');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");  // ** thats how to add image
        if(color.equals(Color.BLACK))
            setStyle("-fx-background-image: url('/images/bpawn');" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: no-repeat");

    }

    public void pick(){
        move();
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
                moves.add(new Move(x, y, x - 1, y - 1));
            }
            if((y-1 >= 0 && x+1 <= 7) && !board.getSquare(x+1,y-1).isEmpty() && board.getSquare(x+1,y-1).getPiece().getColor().equals(Color.BLACK)) {
                moves.add(new Move(x, y, x + 1, y - 1));
            }
        }
        if(color.equals(Color.BLACK)) {
            if(y+1 <= 7 && board.getSquare(x, y+1).isEmpty()){
                moves.add(new Move(x, y, x, y + 1));
            }
            if(y==1 && board.getSquare(x,y+2).isEmpty()){
                moves.add(new Move(x, y, x, y + 2));
            }

            if((y+1 <= 7 && x-1 >= 0) && !board.getSquare(x-1,y+1).isEmpty() && board.getSquare(x-1,y+1).getPiece().getColor().equals(Color.WHITE)){
                moves.add(new Move(x, y, x - 1, y + 1));
            }
            if((y+1 <= 7 && x+1 <= 7) && !board.getSquare(x+1,y+1).isEmpty() && board.getSquare(x+1,y+1).getPiece().getColor().equals(Color.WHITE)) {
                moves.add(new Move(x, y, x + 1, y + 1));
            }
        }

        return moves;
    }

    private boolean moveCheck(Move move){
        boolean movePossible = true;

        if(color.equals(Color.WHITE)){

        }

        return movePossible;
    }

    private void highlightMoves(){

    }


    public void move(){
//        System.out.println("WORKING FROM PAWN");
//        System.out.println("BLACK PAWN CLICKED @ " + this.getX() + " " + this.getY());
//        board.getChessBoard().getChildren().remove(this);
//        board.getChessBoard().add(this, this.getX(), this.getY()+1);
//        this.setX(this.getX());
//        this.setY(this.getY()+1);
        for(Move move : getAvailableMoves()){
            System.out.println(move.toString());
        }
        System.out.println("---------------------------------");
    }




}

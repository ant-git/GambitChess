package pieces;

import game.ChessBoard;
import game.ChessSquare;
import game.Highlightable;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public class Pawn extends Piece implements Highlightable{
    private ChessBoard board;
    private final int maxMoves = 4;
    private ChessSquare square;
    private boolean highlighted;
    private boolean picked;
    private int x;
    private int y;

    private String blackPawnStyle = "-fx-background-image: url('/images/bpawn');" +
                                    "-fx-background-position: center center;" +
                                    "-fx-background-repeat: no-repeat;";

    private String whitePawnStyle = "-fx-background-image: url('/images/wpawn');" +
                                    "-fx-background-position: center center;" +
                                    "-fx-background-repeat: no-repeat";

    private String blackPawnHStyle = "-fx-background-image: url('/images/bpawnh');" +
            "-fx-background-position: center center;" +
            "-fx-background-repeat: no-repeat;";

    private String whitePawnHStyle = "-fx-background-image: url('/images/wpawnh');" +
            "-fx-background-position: center center;" +
            "-fx-background-repeat: no-repeat";

    private String blackPawnPStyle = "-fx-background-image: url('/images/bpawnp');" +
            "-fx-background-position: center center;" +
            "-fx-background-repeat: no-repeat;";

    private String whitePawnPStyle = "-fx-background-image: url('/images/wpawnp');" +
            "-fx-background-position: center center;" +
            "-fx-background-repeat: no-repeat";

    public Pawn(int x, int y, Color color, ChessBoard chessBoard) {
        super(color);
        this.board = chessBoard;
        this.x = x;
        this.y = y;
        square = board.getSquare(this);
        square.setPiece(this);
        highlighted = false;
        picked = false;
        setDefaultIcon();
    }

    public String toString(){
        return "p";
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }


    @Override
    public void setDefaultIcon() {
        if (getColor().equals(Color.WHITE))
            setStyle(whitePawnStyle);  // ** thats how to add image
        if(getColor().equals(Color.BLACK))
            setStyle(blackPawnStyle);

    }

    @Override
    public void setHighlightedIcon() {
        if (getColor().equals(Color.WHITE))
            setStyle(whitePawnHStyle);  // ** thats how to add image
        if(getColor().equals(Color.BLACK))
            setStyle(blackPawnHStyle);
    }

    @Override
    public void setPickIcon() {
        if (getColor().equals(Color.WHITE))
            setStyle(whitePawnPStyle);  // ** thats how to add image
        if(getColor().equals(Color.BLACK))
            setStyle(blackPawnPStyle);
    }

    public void pick(){
        picked = true;
        setPickIcon();
        board.highlightPossibleMoves(getAvailableMoves());
        setOnMouseClicked(event -> {
            board.setDefaultListeners();
        });
        board.setHighlightOnlyListeners(this);
    }

    private ArrayList<Move> getAvailableMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        System.out.println();
        if(getColor().equals(Color.WHITE)) {
            if(y-1 >=0 && board.getSquare(x, y-1).isEmpty()){
                moves.add(new Move(x, y, x, y - 1));
                System.out.println("move #1 added");
            }
            if(y==6 && board.getSquare(x,y-2).isEmpty() && board.getSquare(x,y-1).isEmpty()){
                moves.add(new Move(x, y, x, y - 2));
                System.out.println("move #2 added");
            }
            if((y-1 >= 0 && x-1 >= 0) && !board.getSquare(x-1,y-1).isEmpty() && board.getSquare(x-1,y-1).getPiece().getColor().equals(Color.BLACK)){
                moves.add(new Move(x, y, x - 1, y - 1));
                System.out.println("move #3 added");
            }
            if((y-1 >= 0 && x+1 <= 7) && !board.getSquare(x+1,y-1).isEmpty() && board.getSquare(x+1,y-1).getPiece().getColor().equals(Color.BLACK)) {
                moves.add(new Move(x, y, x + 1, y - 1));
                System.out.println("move #4 added");
            }
        }
        if(getColor().equals(Color.BLACK)) {
            if(y+1 <= 7 && board.getSquare(x, y+1).isEmpty()){
                moves.add(new Move(x, y, x, y + 1));
                System.out.println("move #5 added");
            }
            if(y==1 && board.getSquare(x,y+2).isEmpty() && board.getSquare(x,y+1).isEmpty()){
                moves.add(new Move(x, y, x, y + 2));
                System.out.println("move #6 added");
            }

            if((y+1 <= 7 && x-1 >= 0) && !board.getSquare(x-1,y+1).isEmpty() && board.getSquare(x-1,y+1).getPiece().getColor().equals(Color.WHITE)){
                moves.add(new Move(x, y, x - 1, y + 1));
                System.out.println("move #7 added");
            }
            if((y+1 <= 7 && x+1 <= 7) && !board.getSquare(x+1,y+1).isEmpty() && board.getSquare(x+1,y+1).getPiece().getColor().equals(Color.WHITE)) {
                moves.add(new Move(x, y, x + 1, y + 1));
                System.out.println("move #8 added");
            }
        }
        System.out.println();
        if(moves.size() == 0){
            System.out.println("Not possible to move");
        }
        else{
            System.out.println(moves.toString());
        }
        return moves;
    }

    private boolean moveCheck(Move move){
        boolean movePossible = true;

        if(getColor().equals(Color.WHITE)){

        }

        return movePossible;
    }



    public void move(int newX, int newY){
        board.getSquare(this).removePiece();
        board.getChessBoard().getChildren().remove(this);
        board.getChessBoard().add(this, newX, newY);
        x = newX;
        y = newY;
        board.getSquare(newX,newY).setPiece(this);
        highlighted = false;
        System.out.println("Move successful! Alive pieces left: " + board.getAlivePiecesCount());
    }


    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        setHighlightedIcon();
        this.highlighted = highlighted;
    }

    @Override
    public void highlight(Move move) {
        if(move.getNewX() == getX() && move.getNewY() == getY()){
            setHighlightedIcon();
            highlighted = true;
        }
    }

    @Override
    public void dehighlight() {
        setDefaultIcon();
        highlighted = false;
    }

    public void setDefaultListener(){
        setOnMouseClicked(event -> {
            pick();
            System.out.println("Color: " + getColor().toString());
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
        killer.move(getX(), getY());
    }

}

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
    private ChessSquare square;

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
        super(x, y, color, chessBoard);
        square = getBoard().getSquare(this);
        square.setPiece(this);
        setHighlighted(false);
        setDefaultIcon();
    }

    public String toString(){
        return "p";
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

    @Override
    public ArrayList<Move> getAvailableMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        System.out.println();
        int x = getX();
        int y = getY();

        if(getColor().equals(Color.WHITE)) {
            if(y-1 >=0 && getBoard().getSquare(x, y-1).isEmpty()){
                moves.add(new Move(x, y, x, y - 1));
                System.out.println("move #1 added");
            }
            if(y==6 && getBoard().getSquare(x,y-2).isEmpty() && getBoard().getSquare(x,y-1).isEmpty()){
                moves.add(new Move(x, y, x, y - 2));
                System.out.println("move #2 added");
            }
            if((y-1 >= 0 && x-1 >= 0) && !getBoard().getSquare(x-1,y-1).isEmpty() && getBoard().getSquare(x-1,y-1).getPiece().getColor().equals(Color.BLACK)){
                moves.add(new Move(x, y, x - 1, y - 1));
                System.out.println("move #3 added");
            }
            if((y-1 >= 0 && x+1 <= 7) && !getBoard().getSquare(x+1,y-1).isEmpty() && getBoard().getSquare(x+1,y-1).getPiece().getColor().equals(Color.BLACK)) {
                moves.add(new Move(x, y, x + 1, y - 1));
                System.out.println("move #4 added");
            }
        }
        if(getColor().equals(Color.BLACK)) {
            if(y+1 <= 7 && getBoard().getSquare(x, y+1).isEmpty()){
                moves.add(new Move(x, y, x, y + 1));
                System.out.println("move #5 added");
            }
            if(y==1 && getBoard().getSquare(x,y+2).isEmpty() && getBoard().getSquare(x,y+1).isEmpty()){
                moves.add(new Move(x, y, x, y + 2));
                System.out.println("move #6 added");
            }

            if((y+1 <= 7 && x-1 >= 0) && !getBoard().getSquare(x-1,y+1).isEmpty() && getBoard().getSquare(x-1,y+1).getPiece().getColor().equals(Color.WHITE)){
                moves.add(new Move(x, y, x - 1, y + 1));
                System.out.println("move #7 added");
            }
            if((y+1 <= 7 && x+1 <= 7) && !getBoard().getSquare(x+1,y+1).isEmpty() && getBoard().getSquare(x+1,y+1).getPiece().getColor().equals(Color.WHITE)) {
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




}

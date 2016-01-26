package pieces;

import game.Game;
import game.Highlightable;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public class Pawn extends Piece implements Highlightable{

    private String blackPawnStyle =  generateIcon("/images/bpawn");
    private String whitePawnStyle =  generateIcon("/images/wpawn");
    private String blackPawnHStyle = generateIcon("/images/bpawnh");
    private String whitePawnHStyle = generateIcon("/images/wpawnh");
    private String blackPawnPStyle = generateIcon("/images/bpawnp");
    private String whitePawnPStyle = generateIcon("/images/wpawnp");


    public Pawn(int x, int y, Color color, Game game) {
        super(x, y, color, game);

    }
    public String toString(){
        return "p";
    }


    @Override
    public void setDefaultIcon() {
        setIcon(whitePawnStyle, blackPawnStyle);
    }

    @Override
    public void setHighlightedIcon() {
        setIcon(whitePawnHStyle, blackPawnHStyle);
    }

    @Override
    public void setPickIcon() {
        setIcon(whitePawnPStyle, blackPawnPStyle);
    }

    @Override
    public ArrayList<Move> getAvailableMoves(){

        ArrayList<Move> moves = new ArrayList<>();
        int x = getX();
        int y = getY();

        if(getColor().equals(Color.WHITE)) {
            if(y-1 >=0 && getGame().getSquare(x, y-1).isEmpty()){
                moves.add(new Move(x, y, x, y - 1));
            }
            if(y==6 && getGame().getSquare(x,y-2).isEmpty() && getGame().getSquare(x,y-1).isEmpty()){
                moves.add(new Move(x, y, x, y - 2));
            }
            if((y-1 >= 0 && x-1 >= 0) && !getGame().getSquare(x-1,y-1).isEmpty() && !pieceIsWhiteAtIndex(x-1,y-1)){
                moves.add(new Move(x, y, x - 1, y - 1));
            }
            if((y-1 >= 0 && x+1 <= 7) && !getGame().getSquare(x+1,y-1).isEmpty() && !pieceIsWhiteAtIndex(x+1,y-1)) {
                moves.add(new Move(x, y, x + 1, y - 1));
            }
        }
        if(getColor().equals(Color.BLACK)) {
            if(y+1 <= 7 && getGame().getSquare(x, y+1).isEmpty()){
                moves.add(new Move(x, y, x, y + 1));
            }
            if(y==1 && getGame().getSquare(x,y+2).isEmpty() && getGame().getSquare(x,y+1).isEmpty()){
                moves.add(new Move(x, y, x, y + 2));
            }

            if((y+1 <= 7 && x-1 >= 0) && !getGame().getSquare(x-1,y+1).isEmpty() && pieceIsWhiteAtIndex(x-1,y+1)){
                moves.add(new Move(x, y, x - 1, y + 1));
            }
            if((y+1 <= 7 && x+1 <= 7) && !getGame().getSquare(x+1,y+1).isEmpty() && pieceIsWhiteAtIndex(x+1,y+1)) {
                moves.add(new Move(x, y, x + 1, y + 1));
            }
        }

        return moves;
    }




}

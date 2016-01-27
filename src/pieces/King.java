package pieces;

import game.Game;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public class King extends Piece {
    private String blackKingStyle =  generateIcon("/images/bking");
    private String whiteKingStyle =  generateIcon("/images/wking");
    private String blackKingHStyle = generateIcon("/images/bkingh");
    private String whiteKingHStyle = generateIcon("/images/wkingh");
    private String blackKingPStyle = generateIcon("/images/bkingp");
    private String whiteKingPStyle = generateIcon("/images/wkingp");

    public King(int x, int y, Color color, Game game) {
        super(x, y, color, game);
    }

    public String toString(){
        return "K";
    }


    @Override
    public void setDefaultIcon() {
        setIcon(whiteKingStyle, blackKingStyle);
    }

    @Override
    public void setHighlightedIcon() {
        setIcon(whiteKingHStyle, blackKingHStyle);
    }

    @Override
    public void setPickIcon() {
        setIcon(whiteKingPStyle, blackKingPStyle);
    }

    @Override
    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        int x = getX();
        int y = getY();
        boolean white = pieceIsWhiteAtIndex(x,y);

        if((y-1 >= 0) && (getGame().getSquare(x,y-1).isEmpty() || !pieceIsWhiteAtIndex(x,y-1) == white))
            moves.add(new Move(x, y, x, y - 1));

        if((x-1 >=0 && y-1 >= 0) && (getGame().getSquare(x-1,y-1).isEmpty() || !pieceIsWhiteAtIndex(x-1,y-1) == white))
            moves.add(new Move(x, y, x - 1, y - 1));

        if((x-1 >= 0) && (getGame().getSquare(x-1,y).isEmpty() || !pieceIsWhiteAtIndex(x-1,y) == white))
            moves.add(new Move(x, y, x - 1, y));

        if((x-1 >= 0 && y+1 <= 7) && (getGame().getSquare(x-1,y+1).isEmpty() || !pieceIsWhiteAtIndex(x-1,y+1) == white))
            moves.add(new Move(x, y, x - 1, y + 1));

        if((y+1 <= 7) && (getGame().getSquare(x,y+1).isEmpty() || !pieceIsWhiteAtIndex(x,y+1) == white))
            moves.add(new Move(x, y, x, y + 1));

        if((y+1 <= 7 && x+1 <= 7) && (getGame().getSquare(x+1,y+1).isEmpty() || !pieceIsWhiteAtIndex(x+1,y+1) == white))
            moves.add(new Move(x, y, x + 1, y + 1));

        if((x+1 <= 7) && (getGame().getSquare(x+1,y).isEmpty() || !pieceIsWhiteAtIndex(x+1,y) == white))
            moves.add(new Move(x, y, x + 1, y));

        if((x+1 <= 7 && y-1 >= 0) && (getGame().getSquare(x+1,y-1).isEmpty() || !pieceIsWhiteAtIndex(x+1,y-1) == white))
            moves.add(new Move(x, y, x + 1, y - 1));

        return moves;
    }

    @Override
    public void move(int newX, int newY){
        getGame().getSquare(this).removePiece();
        getGame().getSquare(newX,newY).setPiece(this);
        dehighlight();
        if(getColor().equals(Color.WHITE))
            getGame().setListenersFor(Color.BLACK);
        else
            getGame().setListenersFor(Color.WHITE);
    }

}

package pieces;

import game.Game;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public class Knight extends Piece {


    private String blackKnightStyle =  generateIcon("/images/bknight");
    private String whiteKnightStyle =  generateIcon("/images/wknight");
    private String blackKnightHStyle = generateIcon("/images/bknighth");
    private String whiteKnightHStyle = generateIcon("/images/wknighth");
    private String blackKnightPStyle = generateIcon("/images/bknightp");
    private String whiteKnightPStyle = generateIcon("/images/wknightp");

    public Knight(int x, int y, Color color, Game game) {
        super(x, y, color, game);
    }

    public String toString(){
        return "k";
    }

    @Override
    public void setDefaultIcon() {
        setIcon(whiteKnightStyle, blackKnightStyle);
    }

    @Override
    public void setHighlightedIcon() {
        setIcon(whiteKnightHStyle, blackKnightHStyle);
    }

    @Override
    public void setPickIcon() {
        setIcon(whiteKnightPStyle, blackKnightPStyle);

    }

    @Override
    public ArrayList<Move> getAvailableMoves() {

        ArrayList<Move> moves = new ArrayList<>();
        int x = getX();
        int y = getY();
        boolean white = pieceIsWhiteAtIndex(x,y);

        //x-1 y-2
        if((x - 1 >=0 && y - 2 >= 0) && (getGame().getSquare(x-1,y-2).isEmpty() || pieceIsWhiteAtIndex(x-1 , y-2) != white))
            moves.add(new Move(x, y, x - 1, y - 2));
        //x+1 y-2
        if((x + 1 <= 7 && y - 2 >= 0) && (getGame().getSquare(x+1,y-2).isEmpty() || pieceIsWhiteAtIndex(x+1 , y-2) != white))
            moves.add(new Move(x, y, x + 1, y - 2));
        //x-2 y-1
        if((x - 2 >=0 && y - 1 >= 0) && (getGame().getSquare(x-2,y-1).isEmpty() || pieceIsWhiteAtIndex(x-2 , y-1) != white))
            moves.add(new Move(x, y, x - 2, y - 1));
        //x+2 y-1
        if((x + 2 <= 7 && y - 1 >= 0) && (getGame().getSquare(x+2,y-1).isEmpty() || pieceIsWhiteAtIndex(x+2 , y-1) != white))
            moves.add(new Move(x, y, x + 2, y - 1));
        //x-2 y+1
        if((x - 2 >=0 && y + 1 <= 7) && (getGame().getSquare(x-2,y+1).isEmpty() || pieceIsWhiteAtIndex(x-2 , y+1) != white))
            moves.add(new Move(x, y, x - 2, y + 1));
        //x-1 y+2
        if((x - 1 >=0 && y + 2 <= 7) && (getGame().getSquare(x-1,y+2).isEmpty() || pieceIsWhiteAtIndex(x-1 , y+2) != white))
            moves.add(new Move(x, y, x - 1, y + 2));
        //x+1 y+2
        if((x + 1 <= 7 && y + 2 <= 7) && (getGame().getSquare(x+1,y+2).isEmpty() || pieceIsWhiteAtIndex(x+1 , y+2) != white))
            moves.add(new Move(x, y, x + 1, y + 2));
        //x+2 y+1
        if((x + 2 <= 7 && y + 1 <= 7) && (getGame().getSquare(x+2,y+1).isEmpty() || pieceIsWhiteAtIndex(x+2 , y+1) != white))
            moves.add(new Move(x, y, x + 2, y + 1));

        return moves;
    }


}

package pieces;

import game.Game;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Anton
 * Knight piece class
 */
public class Knight extends Piece {


    private String blackKnightStyle =  generateIcon("/images/bknight");
    private String whiteKnightStyle =  generateIcon("/images/wknight");
    private String blackKnightHStyle = generateIcon("/images/bknighth");
    private String whiteKnightHStyle = generateIcon("/images/wknighth");
    private String blackKnightPStyle = generateIcon("/images/bknightp");
    private String whiteKnightPStyle = generateIcon("/images/wknightp");
    private Game game;

    public Knight(int x, int y, Color color, Game game) {
        super(x, y, color, game);
        this.game = game;
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
    public String getDefaultIconPath() {
        if(isWhite())
            return "/images/wknight";
        else
            return "/images/bknight";
    }

    @Override
    public ArrayList<Move> getAvailableMoves() {

        ArrayList<Move> moves = new ArrayList<>();
        int x = getX();
        int y = getY();
        boolean white = game.pieceIsWhiteAtIndex(x,y);

        //x-1 y-2
        if((x - 1 >=0 && y - 2 >= 0) && (getGame().getSquare(x-1,y-2).isEmpty() || game.pieceIsWhiteAtIndex(x-1 , y-2) != white))
            moves.add(new Move(x, y, x - 1, y - 2));
        //x+1 y-2
        if((x + 1 <= 7 && y - 2 >= 0) && (getGame().getSquare(x+1,y-2).isEmpty() || game.pieceIsWhiteAtIndex(x+1 , y-2) != white))
            moves.add(new Move(x, y, x + 1, y - 2));
        //x-2 y-1
        if((x - 2 >=0 && y - 1 >= 0) && (getGame().getSquare(x-2,y-1).isEmpty() || game.pieceIsWhiteAtIndex(x-2 , y-1) != white))
            moves.add(new Move(x, y, x - 2, y - 1));
        //x+2 y-1
        if((x + 2 <= 7 && y - 1 >= 0) && (getGame().getSquare(x+2,y-1).isEmpty() || game.pieceIsWhiteAtIndex(x+2 , y-1) != white))
            moves.add(new Move(x, y, x + 2, y - 1));
        //x-2 y+1
        if((x - 2 >=0 && y + 1 <= 7) && (getGame().getSquare(x-2,y+1).isEmpty() || game.pieceIsWhiteAtIndex(x-2 , y+1) != white))
            moves.add(new Move(x, y, x - 2, y + 1));
        //x-1 y+2
        if((x - 1 >=0 && y + 2 <= 7) && (getGame().getSquare(x-1,y+2).isEmpty() || game.pieceIsWhiteAtIndex(x-1 , y+2) != white))
            moves.add(new Move(x, y, x - 1, y + 2));
        //x+1 y+2
        if((x + 1 <= 7 && y + 2 <= 7) && (getGame().getSquare(x+1,y+2).isEmpty() || game.pieceIsWhiteAtIndex(x+1 , y+2) != white))
            moves.add(new Move(x, y, x + 1, y + 2));
        //x+2 y+1
        if((x + 2 <= 7 && y + 1 <= 7) && (getGame().getSquare(x+2,y+1).isEmpty() || game.pieceIsWhiteAtIndex(x+2 , y+1) != white))
            moves.add(new Move(x, y, x + 2, y + 1));

        return moves;
    }


}

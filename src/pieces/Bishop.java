package pieces;

import game.Game;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public class Bishop extends Piece {

    private String blackBishopStyle =  generateIcon("/images/bbishop");
    private String whiteBishopStyle =  generateIcon("/images/wbishop");
    private String blackBishopHStyle = generateIcon("/images/bbishoph");
    private String whiteBishopHStyle = generateIcon("/images/wbishoph");
    private String blackBishopPStyle = generateIcon("/images/bbishopp");
    private String whiteBishopPStyle = generateIcon("/images/wbishopp");

    public Bishop(int x, int y, Color color, Game game) {
        super(x, y, color, game);
    }

    public String toString(){
        return "b";
    }


    @Override
    public void setDefaultIcon() {
        setIcon(whiteBishopStyle, blackBishopStyle);
    }

    @Override
    public void setHighlightedIcon() {
        setIcon(whiteBishopHStyle, blackBishopHStyle);
    }

    @Override
    public void setPickIcon() {
        setIcon(whiteBishopPStyle, blackBishopPStyle);

    }


    @Override
    public ArrayList<Move> getAvailableMoves() {

        return getDiagonalMoves();
    }


}

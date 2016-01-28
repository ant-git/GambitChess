package pieces;

import game.Game;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public class Rook extends Piece {

    private String blackRookStyle =  generateIcon("/images/brook");
    private String whiteRookStyle =  generateIcon("/images/wrook");
    private String blackRookHStyle = generateIcon("/images/brookh");
    private String whiteRookHStyle = generateIcon("/images/wrookh");
    private String blackRookPStyle = generateIcon("/images/brookp");
    private String whiteRookPStyle = generateIcon("/images/wrookp");

    public Rook(int x, int y, Color color, Game game) {
        super(x, y, color, game);
    }

    public String toString(){
        return "r";
    }


    @Override
    public void setDefaultIcon() {
        setIcon(whiteRookStyle,blackRookStyle);
    }

    @Override
    public void setHighlightedIcon() {
        setIcon(whiteRookHStyle,blackRookHStyle);
    }

    @Override
    public void setPickIcon() {
        setIcon(whiteRookPStyle,blackRookPStyle);
    }


    @Override
    public ArrayList<Move> getAvailableMoves() {

        return getAxisMoves();
    }
}

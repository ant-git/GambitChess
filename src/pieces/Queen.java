package pieces;

import game.ChessBoard;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public class Queen extends Piece {

    private String blackQueenStyle =  generateIcon("/images/bqueen");
    private String whiteQueenStyle =  generateIcon("/images/wqueen");
    private String blackQueenHStyle = generateIcon("/images/bqueenh");
    private String whiteQueenHStyle = generateIcon("/images/wqueenh");
    private String blackQueenPStyle = generateIcon("/images/bqueenp");
    private String whiteQueenPStyle = generateIcon("/images/wqueenp");

    public Queen(int x, int y, Color color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
    }

    public String toString(){
        return "Q";
    }


    @Override
    public void setDefaultIcon() {
        setIcon(whiteQueenStyle, blackQueenStyle);
    }

    @Override
    public void setHighlightedIcon() {
        setIcon(whiteQueenHStyle, blackQueenHStyle);
    }

    @Override
    public void setPickIcon() {
        setIcon(whiteQueenPStyle, blackQueenPStyle);
    }

    @Override
    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(getDiagonalMoves());
        moves.addAll(getAxisMoves());

        return moves;

    }


}

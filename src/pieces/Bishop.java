package pieces;

import game.ChessBoard;
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

    public Bishop(int x, int y, Color color, ChessBoard chessBoard) {
        super(x, y, color, chessBoard);
        setDefaultIcon();
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
        ArrayList<Move> moves = new ArrayList<>();
        int x = getX();
        int y = getY();
        boolean white = pieceIsWhiteAtIndex(x,y);

        int i = 1;
        while(x-i >=0 && y-i >= 0){
            if(getBoard().getSquare(x-i,y-i).isEmpty()){
                moves.add(new Move(x, y, x - i, y - i));
            }else{
                if(pieceIsWhiteAtIndex(x-i, y-i) != white){
                    moves.add(new Move(x, y, x - i, y - i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x+i <= 7 && y-i >= 0){
            if(getBoard().getSquare(x+i,y-i).isEmpty()){
                moves.add(new Move(x, y, x + i, y - i));
            }else{
                if(pieceIsWhiteAtIndex(x+i, y-i) != white){
                    moves.add(new Move(x, y, x + i, y - i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x-i >= 0 && y+i <= 7){
            if(getBoard().getSquare(x-i,y+i).isEmpty()){
                moves.add(new Move(x, y, x - i, y + i));
            }else{
                if(pieceIsWhiteAtIndex(x-i, y+i) != white){
                    moves.add(new Move(x, y, x - i, y + i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x+i <= 7 && y+i <= 7){
            if(getBoard().getSquare(x+i,y+i).isEmpty()){
                moves.add(new Move(x, y, x + i, y + i));
            }else{
                if(pieceIsWhiteAtIndex(x+i, y+i) != white){
                    moves.add(new Move(x, y, x + i, y + i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        return moves;
    }


}

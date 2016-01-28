package pieces;

import game.ChessSquare;
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

        if(getColor().equals(Color.BLACK)){
            if(!getGame().getSquare(7,0).isEmpty()
                    &&  getGame().getPiece(7,0).getMoveCount() == 0
                    && getGame().getSquare(5,0).isEmpty()
                    && getGame().getSquare(6,0).isEmpty()) {

                if(isCastlingAvailable(5,0,getEnemyColor())
                        && (getGame().getSquare(6,1).isEmpty()
                        || !(getGame().getPiece(6,1).getColor().equals(Color.BLACK)&& getGame().getPiece(6,1) instanceof Pawn))
                        && (getGame().getSquare(4,1).isEmpty()
                        || !(getGame().getPiece(4,1).getColor().equals(Color.BLACK)&& getGame().getPiece(4,1) instanceof Pawn)))
                    moves.add(new Move(x, y, 6, 0));

            }
            if(!getGame().getSquare(0,0).isEmpty()
                    &&  getGame().getPiece(0,0).getMoveCount() == 0
                    && getGame().getSquare(1,0).isEmpty()
                    && getGame().getSquare(2,0).isEmpty()
                    && getGame().getSquare(3,0).isEmpty()) {

                if(isCastlingAvailable(3,0,getEnemyColor())
                        && (getGame().getSquare(2,1).isEmpty()
                        || !(getGame().getPiece(2,1).getColor().equals(Color.WHITE) && getGame().getPiece(2,1) instanceof Pawn))
                        && (getGame().getSquare(4,1).isEmpty()
                        || !(getGame().getPiece(4,1).getColor().equals(Color.WHITE)&& getGame().getPiece(4,1) instanceof Pawn)))
                    moves.add(new Move(x, y, 2, 0));

            }
        }

        if(getColor().equals(Color.WHITE)){
           if(!getGame().getSquare(7,7).isEmpty()
                   &&  getGame().getSquare(7,7).getPiece().getMoveCount() == 0
                   && getGame().getSquare(6,7).isEmpty()
                   && getGame().getSquare(5,7).isEmpty()) {

               if(isCastlingAvailable(5,7,getEnemyColor())
                       && (getGame().getSquare(4,6).isEmpty()
                       || !(getGame().getPiece(4,6).getColor().equals(Color.BLACK)&& getGame().getPiece(4,6) instanceof Pawn))
                       && (getGame().getSquare(6,6).isEmpty()
                       || !(getGame().getPiece(6,6).getColor().equals(Color.BLACK)&& getGame().getPiece(6,6) instanceof Pawn)))
                    moves.add(new Move(x, y, 6, 7));
           }
           if(!getGame().getSquare(0,7).isEmpty()
                   &&  getGame().getSquare(0,7).getPiece().getMoveCount() == 0
                   && getGame().getSquare(1,7).isEmpty()
                   && getGame().getSquare(2,7).isEmpty()
                   && getGame().getSquare(3,7).isEmpty()) {

               if(isCastlingAvailable(3,7,getEnemyColor())
                       && (getGame().getSquare(4,6).isEmpty()
                       || !(getGame().getPiece(4,6).getColor().equals(Color.WHITE) && getGame().getPiece(4,6) instanceof Pawn))
                       && (getGame().getSquare(2,6).isEmpty()
                       || !(getGame().getPiece(2,6).getColor().equals(Color.WHITE) && getGame().getPiece(2,6) instanceof Pawn)))
                    moves.add(new Move(x, y, 2, 7));
           }
        }

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
        getGame().addMoveToList(new Move(getX(),getY(), newX, newY));
        getGame().getSquare(this).removePiece();
        getGame().getSquare(newX,newY).setPiece(this);
        dehighlight();
        if(getColor().equals(Color.WHITE))
            getGame().setListenersFor(Color.BLACK);
        else
            getGame().setListenersFor(Color.WHITE);
        increaseMoveCount();
        getGame().setLastMovedPiece(this);
    }

    private boolean isCastlingAvailable(int x, int y, Color color){

        ArrayList<Move> moves = getGame().getPossibleMovesForAll(color);
        for(Move move : moves){
            if(move.getNewX()==x && move.getNewY()==y){
                return false;
            }
        }

        return true;
    }

}

package pieces;

import game.ChessSquare;
import game.Game;
import game.Move;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Anton
 * King piece class
 */
public class King extends Piece {
    private String blackKingStyle =  generateIcon("/images/bking");
    private String whiteKingStyle =  generateIcon("/images/wking");
    private String blackKingHStyle = generateIcon("/images/bkingh");
    private String whiteKingHStyle = generateIcon("/images/wkingh");
    private String blackKingPStyle = generateIcon("/images/bkingp");
    private String whiteKingPStyle = generateIcon("/images/wkingp");
    private Game game;

    public King(int x, int y, Color color, Game game) {
        super(x, y, color, game);
        this.game = game;
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
    public String getDefaultIconPath() {
        if(isWhite())
            return "/images/wking";
        else
            return "/images/bking";
    }

    @Override
    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        int x = getX();
        int y = getY();
        boolean white = game.pieceIsWhiteAtIndex(x,y);

        if(getColor().equals(Color.BLACK)){
            if(!game.getSquare(7,0).isEmpty()
                    &&  game.getPiece(7,0).getMoveCount() == 0
                    && game.getSquare(5,0).isEmpty()
                    && game.getSquare(6,0).isEmpty()) {

                if(isCastlingAvailable(5,0,getEnemyColor())
                        && (game.getSquare(6,1).isEmpty() || !(game.getPiece(6,1).getColor().equals(Color.WHITE) && game.getPiece(6,1) instanceof Pawn))
                        && (game.getSquare(4,1).isEmpty() || !(game.getPiece(4,1).getColor().equals(Color.WHITE)&& game.getPiece(4,1) instanceof Pawn)))
                    moves.add(new Move(x, y, 6, 0));

            }
            if(!game.getSquare(0,0).isEmpty()
                    &&  game.getPiece(0,0).getMoveCount() == 0
                    && game.getSquare(1,0).isEmpty()
                    && game.getSquare(2,0).isEmpty()
                    && game.getSquare(3,0).isEmpty()) {

                if(isCastlingAvailable(3,0,getEnemyColor())
                        && (game.getSquare(2,1).isEmpty() || !(game.getPiece(2,1).getColor().equals(Color.WHITE) && game.getPiece(2,1) instanceof Pawn))
                        && (game.getSquare(4,1).isEmpty() || !(game.getPiece(4,1).getColor().equals(Color.WHITE) && game.getPiece(4,1) instanceof Pawn)))
                    moves.add(new Move(x, y, 2, 0));

            }
        }

        if(getColor().equals(Color.WHITE)){
           if(!game.getSquare(7,7).isEmpty()
                   && game.getSquare(7,7).getPiece().getMoveCount() == 0
                   && game.getSquare(6,7).isEmpty()
                   && game.getSquare(5,7).isEmpty()) {

               if(isCastlingAvailable(5,7,getEnemyColor())
                       && (game.getSquare(4,6).isEmpty() || !(game.getPiece(4,6).getColor().equals(Color.BLACK)&& game.getPiece(4,6) instanceof Pawn))
                       && (game.getSquare(6,6).isEmpty() || !(game.getPiece(6,6).getColor().equals(Color.BLACK)&& game.getPiece(6,6) instanceof Pawn)))
                    moves.add(new Move(x, y, 6, 7));
           }
           if(!game.getSquare(0,7).isEmpty()
                   &&  game.getSquare(0,7).getPiece().getMoveCount() == 0
                   && game.getSquare(1,7).isEmpty()
                   && game.getSquare(2,7).isEmpty()
                   && game.getSquare(3,7).isEmpty()) {

               if(isCastlingAvailable(3,7,getEnemyColor())
                       && (game.getSquare(4,6).isEmpty() || !(game.getPiece(4,6).getColor().equals(Color.BLACK) && game.getPiece(4,6) instanceof Pawn))
                       && (game.getSquare(2,6).isEmpty() || !(game.getPiece(2,6).getColor().equals(Color.BLACK) && game.getPiece(2,6) instanceof Pawn)))
                    moves.add(new Move(x, y, 2, 7));
           }
        }

        if((y-1 >= 0) && (game.getSquare(x,y-1).isEmpty() || !game.pieceIsWhiteAtIndex(x,y-1) == white))
            moves.add(new Move(x, y, x, y - 1));

        if((x-1 >=0 && y-1 >= 0) && (game.getSquare(x-1,y-1).isEmpty() || !game.pieceIsWhiteAtIndex(x-1,y-1) == white))
            moves.add(new Move(x, y, x - 1, y - 1));

        if((x-1 >= 0) && (game.getSquare(x-1,y).isEmpty() || !game.pieceIsWhiteAtIndex(x-1,y) == white))
            moves.add(new Move(x, y, x - 1, y));

        if((x-1 >= 0 && y+1 <= 7) && (game.getSquare(x-1,y+1).isEmpty() || !game.pieceIsWhiteAtIndex(x-1,y+1) == white))
            moves.add(new Move(x, y, x - 1, y + 1));

        if((y+1 <= 7) && (game.getSquare(x,y+1).isEmpty() || !game.pieceIsWhiteAtIndex(x,y+1) == white))
            moves.add(new Move(x, y, x, y + 1));

        if((y+1 <= 7 && x+1 <= 7) && (game.getSquare(x+1,y+1).isEmpty() || !game.pieceIsWhiteAtIndex(x+1,y+1) == white))
            moves.add(new Move(x, y, x + 1, y + 1));

        if((x+1 <= 7) && (game.getSquare(x+1,y).isEmpty() || !game.pieceIsWhiteAtIndex(x+1,y) == white))
            moves.add(new Move(x, y, x + 1, y));

        if((x+1 <= 7 && y-1 >= 0) && (game.getSquare(x+1,y-1).isEmpty() || !game.pieceIsWhiteAtIndex(x+1,y-1) == white))
            moves.add(new Move(x, y, x + 1, y - 1));

        return moves;
    }

    @Override
    public void move(int newX, int newY){
        game.addMoveToList(new Move(getX(),getY(), newX, newY));
        game.getSquare(this).removePiece();
        game.getSquare(newX,newY).setPiece(this);
        dehighlight();
        if(getColor().equals(Color.WHITE))
            game.setListenersFor(Color.BLACK);
        else
            game.setListenersFor(Color.WHITE);
        increaseMoveCount();
        game.setLastMovedPiece(this);
    }

    //to check is there is  a possibility for Castling move
    private boolean isCastlingAvailable(int x, int y, Color color){

        ArrayList<Move> moves = game.getPossibleMovesForAll(color);
        for(Move move : moves){
            if(move.getNewX()==x && move.getNewY()==y){
                return false;
            }
        }

        return true;
    }

}

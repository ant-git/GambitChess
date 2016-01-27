package pieces;

import game.Game;
import game.ChessSquare;
import game.Move;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by antant on 21/01/16.
 */
public abstract class Piece extends Pane{
    private Color color;
    private int x;
    private int y;
    private Game game;
    private boolean highlighted;
    private ChessSquare square;

    public Piece(int x, int y, Color color, Game game) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.game = game;
        highlighted = false;
        square = game.getSquare(this);
        setDefaultIcon();

    }

    public String toString() {
        return "P";
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setIcon(String whiteIcon, String blackIcon){
        if (getColor().equals(Color.WHITE))
            setStyle(whiteIcon);  // ** thats how to add image
        if(getColor().equals(Color.BLACK))
            setStyle(blackIcon);
    }

    public abstract void setDefaultIcon();

    public abstract void setHighlightedIcon();

    public abstract void setPickIcon();

    public Game getGame() {
        return game;
    }


    public void dehighlight() {
        setDefaultIcon();
        highlighted = false;
    }



    public boolean pieceIsWhiteAtIndex(int x, int y){
        return game.getSquare(x,y).getPiece().getColor().equals(Color.WHITE);
    }

    public boolean isHighlighted() {
        return highlighted;
    }


    public void move(int newX, int newY){
        game.getSquare(this).removePiece();
        game.getSquare(newX,newY).setPiece(this);
        highlighted = false;
        game.dehighlightAllMoves();

        if(getColor().equals(Color.WHITE)) {
            System.out.println("BLACK TURN");
            game.setListenersFor(Color.BLACK);
        }
        else {
            System.out.println("WHITE TURN");
            game.setListenersFor(Color.WHITE);

        }

        if(game.isKingUnderTreat(getEnemyColor())){
            System.out.println("CHECK!");
            for(Node node : game.getChessBoard().getChildren()){
                if(node instanceof Piece && ((Piece) node).getColor().equals(getEnemyColor())){
                    System.out.println(((Piece) node).getAvailableMoves().toString());
                }
            }
        }

    }


    public void setUnderTreatListener(Piece killer){
        setOnMouseClicked(event -> {
            killer.kill(this);
            game.dehighlightAllMoves();
        });
    }

    public void highlight(Move move) {
        if(move.getNewX() == getX() && move.getNewY() == getY()){
            setHighlightedIcon();
            highlighted = true;
        }
    }

    public void setDefaultListener(){
        setOnMouseClicked(event -> {
            pick();
        });
    }

    public void setDeselectListener(){
        setOnMouseClicked(event -> {
            game.dehighlightAllMoves();
            game.setListenersFor(getColor());
        });
    }

    public void removeListeners(){
        setOnMouseClicked(event -> {});
    }



    public boolean isMoveSafe(Move move){
        boolean check = false;
        game.getSquare(this).removePiece();
        game.getChessBoard().getChildren().remove(this);
        if(!game.getSquare(move.getNewX(), move.getNewY()).isEmpty()){
            Piece piece = game.getSquare(move.getNewX(), move.getNewY()).getPiece();
            game.getSquare(piece).removePiece();
            game.getSquare(move.getNewX(), move.getNewY()).setPiece(this);
            if(game.isKingUnderTreat(color))
                check = true;
            game.getSquare(this).removePiece();
            game.getSquare(move.getX(), move.getY()).setPiece(this);
            game.getSquare(move.getNewX(), move.getNewY()).setPiece(piece);

        }
        else{
            game.getSquare(move.getNewX(), move.getNewY()).setPiece(this);
            if(game.isKingUnderTreat(color))
                check = true;
            game.getSquare(this).removePiece();
            game.getSquare(move.getX(), move.getY()).setPiece(this);

        }


        return !check;
    }

    public ArrayList<Move> getAllSafeMoves(){
        ArrayList<Move> movesWithoutCheck = new ArrayList<>();
        for (Move move : getAvailableMoves())
        {
            if(isMoveSafe(move))
                movesWithoutCheck.add(move);
        }

        return movesWithoutCheck;
    }


    public abstract ArrayList<Move> getAvailableMoves();

    public void pick() {
        setPickIcon();
        game.highlightPossibleMoves(getAllSafeMoves());
        setDeselectListener();
        game.setHighlightOnlyListeners(this);

    }

    public void kill(Piece victim){
        game.getChessBoard().getChildren().remove(victim);
        game.getSquare(victim).removePiece();
        this.move(victim.getX(), victim.getY());
    }

    public ArrayList<Move> getDiagonalMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        boolean white = pieceIsWhiteAtIndex(x,y);

        int i = 1;
        while(x-i >=0 && y-i >= 0){
            if(getGame().getSquare(x-i,y-i).isEmpty()){
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
            if(getGame().getSquare(x+i,y-i).isEmpty()){
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
            if(getGame().getSquare(x-i,y+i).isEmpty()){
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
            if(getGame().getSquare(x+i,y+i).isEmpty()){
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

    public ArrayList<Move> getAxisMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        boolean white = pieceIsWhiteAtIndex(x,y);

        int i = 1;
        while(y-i >= 0){
            if(getGame().getSquare(x,y-i).isEmpty()){
                moves.add(new Move(x, y, x, y - i));
            }else{
                if(pieceIsWhiteAtIndex(x, y-i) != white){
                    moves.add(new Move(x, y, x, y - i));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x-i >= 0){
            if(getGame().getSquare(x-i,y).isEmpty()){
                moves.add(new Move(x, y, x-i, y));
            }else{
                if(pieceIsWhiteAtIndex(x-i, y) != white){
                    moves.add(new Move(x, y, x - i, y));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(x+i <= 7){
            if(getGame().getSquare(x+i,y).isEmpty()){
                moves.add(new Move(x, y, x+i, y));
            }else{
                if(pieceIsWhiteAtIndex(x+i, y) != white){
                    moves.add(new Move(x, y, x+i, y));
                    break;
                }
                else {
                    break;
                }
            }
            i++;
        }

        i = 1;
        while(y+i <= 7){
            if(getGame().getSquare(x,y+i).isEmpty()){
                moves.add(new Move(x, y, x, y + i));
            }else{
                if(pieceIsWhiteAtIndex(x, y+i) != white){
                    moves.add(new Move(x, y, x, y + i));
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

    public String generateIcon(String url){
        return "-fx-background-image: url('" + url + "');" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: no-repeat";
    }

    public boolean isWhite(){
        if(color.equals(Color.WHITE))
            return true;
        else
            return false;
    }
    public Color getEnemyColor(){
        if(isWhite())
            return Color.BLACK;
        else
            return Color.WHITE;
    }


}

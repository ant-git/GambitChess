package pieces;

import game.Game;
import game.Move;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Anton
 * Abstract class for all Pieces
 */
public abstract class Piece extends Pane{
    private Color color;
    private int x;
    private int y;
    private Game game;
    private boolean highlighted;
    private int moveCount;

    public Piece(int x, int y, Color color, Game game) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.game = game;
        moveCount = 0;
        highlighted = false;
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





    public boolean isHighlighted() {
        return highlighted;
    }


    public void move(int newX, int newY){
        game.addMoveToList(new Move(getX(),getY(), newX, newY));
        game.getSquare(this).removePiece();
        game.getSquare(newX,newY).setPiece(this);


        if(newY == 0 && this instanceof Pawn && getColor().equals(Color.WHITE)
                ||(newY == 7 && this instanceof Pawn && getColor().equals(Color.BLACK)) ){
            game.promotePawn(this);
        }

        highlighted = false;
        game.dehighlightAllMoves();

        if(getColor().equals(Color.WHITE)) {
            game.setListenersFor(Color.BLACK);
        }
        else {
            game.setListenersFor(Color.WHITE);

        }

        increaseMoveCount();
        game.setLastMovedPiece(this);

        if(game.countSafeMovesFor(getEnemyColor()) == 0){
            game.removeListeners();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("CHECKMATE");
            alert.setHeaderText(null);
            alert.setContentText("CHECKMATE");
            alert.showAndWait();
        }
        else{
            if(game.isKingUnderThreat(getEnemyColor())){
                game.getKing(getEnemyColor());
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
            getSelected();
        });
    }

    public void setDeselectListener(){
        setOnMouseClicked(event -> {
            game.dehighlightAllMoves();
            game.setListenersFor(getColor());
        });
    }

    public void removeListener(){
        setOnMouseClicked(event -> {});
    }


    //method to check if the move is safe and friendly King is not under attack
    public boolean isMoveSafe(Move move){
        boolean check = false;
        remove();
        if(!game.getSquare(move.getNewX(), move.getNewY()).isEmpty()){
            Piece piece = game.getSquare(move.getNewX(), move.getNewY()).getPiece();

            game.getSquare(piece).removePiece();
            game.getSquare(move.getNewX(), move.getNewY()).setPiece(this);

            if(game.isKingUnderThreat(color))
                check = true;


            game.getSquare(this).removePiece();
            game.getSquare(move.getX(), move.getY()).setPiece(this);
            game.getSquare(move.getNewX(), move.getNewY()).setPiece(piece);



        }
        else{
            game.getSquare(move.getNewX(), move.getNewY()).setPiece(this);
            if(game.isKingUnderThreat(color))
                check = true;
            game.getSquare(this).removePiece();
            game.getSquare(move.getX(), move.getY()).setPiece(this);
        }


        return !check;
    }

    //to filter all the dangerous moves for friendly King
    public ArrayList<Move> filterMoves(){
        ArrayList<Move> movesWithoutCheck = new ArrayList<>();
        for (Move move : getAvailableMoves())
        {
            if(isMoveSafe(move))
                movesWithoutCheck.add(move);
        }
        return movesWithoutCheck;
    }


    public abstract ArrayList<Move> getAvailableMoves();


    public void getSelected() {
        setPickIcon();
        game.highlightPossibleMoves(filterMoves());
        setDeselectListener();
        game.setHighlightOnlyListeners(this);
    }

    //to kill another piece
    public void kill(Piece victim){
        game.getSquare(victim).removePiece();
        game.getChessBoard().getChildren().remove(victim);
        this.move(victim.getX(), victim.getY());
    }

    //method returns list of Diagonal moves (required for Queen and Bishop)
    public ArrayList<Move> getDiagonalMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        boolean white = game.pieceIsWhiteAtIndex(x,y);

        int i = 1;
        while(x-i >=0 && y-i >= 0){
            if(getGame().getSquare(x-i,y-i).isEmpty()){
                moves.add(new Move(x, y, x - i, y - i));
            }else{
                if(game.pieceIsWhiteAtIndex(x-i, y-i) != white){
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
                if(game.pieceIsWhiteAtIndex(x+i, y-i) != white){
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
                if(game.pieceIsWhiteAtIndex(x-i, y+i) != white){
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
                if(game.pieceIsWhiteAtIndex(x+i, y+i) != white){
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

    //method returns list of Horizontal-Vertical moves (required for Queen and Rook)
    public ArrayList<Move> getAxisMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        boolean white = game.pieceIsWhiteAtIndex(x,y);

        int i = 1;
        while(y-i >= 0){
            if(getGame().getSquare(x,y-i).isEmpty()){
                moves.add(new Move(x, y, x, y - i));
            }else{
                if(game.pieceIsWhiteAtIndex(x, y-i) != white){
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
                if(game.pieceIsWhiteAtIndex(x-i, y) != white){
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
                if(game.pieceIsWhiteAtIndex(x+i, y) != white){
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
                if(game.pieceIsWhiteAtIndex(x, y+i) != white){
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

    public int getMoveCount() {
        return moveCount;
    }

    public void increaseMoveCount(){
        moveCount++;
    }

    public void remove(){
        game.getSquare(this).removePiece();
        game.getChessBoard().getChildren().remove(this);
    }

}

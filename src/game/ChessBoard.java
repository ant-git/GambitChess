package game;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pieces.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by antant on 21/01/16.
 */
public class ChessBoard  implements Initializable{

    @FXML
    GridPane chessBoard;
    boolean clicked = false;
    int whiteKingX;
    int whiteKingY;
    int blackKingX;
    int blackKingY;
    boolean whiteTurn;

    public ChessBoard() {


    }

    public void paintBoard(){
        boolean white = true;
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                ChessSquare square;
                if(white) {
                    square = new ChessSquare(j,i,Color.WHITE, this);
                    chessBoard.add(square,j,i);
                    white = false;
                }else{
                    square = new ChessSquare(j,i,Color.BLACK, this);
                    chessBoard.add(square,j,i);
                    white = true;
                }
                if(j== 7){
                    white = !white;
                }

            }
        }

    }


    public  void setupPieces(){
        for(int i=0; i < 8; i++){
            Pawn pawn = new Pawn(i,1,Color.BLACK, this);
            chessBoard.add(pawn,i,1);
        }

        for(int i=0; i < 8; i++){
            Pawn pawn = new Pawn(i,6,Color.WHITE, this);
            chessBoard.add(pawn,i,6);
        }

        King bking = new King(4,0, Color.BLACK, this);
        chessBoard.add(bking, 4,0);
        setBlackKingX(4);
        setBlackKingY(0);
        King wking = new King(4,7, Color.WHITE, this);
        chessBoard.add(wking, 4,7);
        setWhiteKingX(4);
        setWhiteKingY(7);
        Rook brook1 = new Rook(0,0,Color.BLACK, this);
        chessBoard.add(brook1, 0,0);
        Rook brook2 = new Rook(7,0,Color.BLACK, this);
        chessBoard.add(brook2, 7,0);
        Rook wrook1 = new Rook(0,7,Color.WHITE, this);
        chessBoard.add(wrook1, 0,7);
        Rook wrook2 = new Rook(7,7,Color.WHITE, this);
        chessBoard.add(wrook2, 7,7);

        Queen bqueen = new Queen(3,0,Color.BLACK, this);
        chessBoard.add(bqueen,3,0);
        Queen wqueen = new Queen(3,7,Color.WHITE, this);
        chessBoard.add(wqueen,3,7);

        Bishop bbishop1 = new Bishop(2,0,Color.BLACK, this);
        chessBoard.add(bbishop1,2,0);
        Bishop bbishop2 = new Bishop(5,0,Color.BLACK, this);
        chessBoard.add(bbishop2,5,0);
        Bishop wbishop1 = new Bishop(2,7,Color.WHITE, this);
        chessBoard.add(wbishop1,2,7);
        Bishop wbishop2 = new Bishop(5,7,Color.WHITE, this);
        chessBoard.add(wbishop2,5,7);

        Knight bknight1 = new Knight(1,0,Color.BLACK, this);
        chessBoard.add(bknight1,1,0);
        Knight bknight2 = new Knight(6,0,Color.BLACK, this);
        chessBoard.add(bknight2,6,0);
        Knight wknight1 = new Knight(1,7,Color.WHITE, this);
        chessBoard.add(wknight1,1,7);
        Knight wknight2 = new Knight(6,7,Color.WHITE, this);
        chessBoard.add(wknight2,6,7);

        dehighlightAllMoves();

    }


    public ChessSquare getSquare(int x, int y){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof ChessSquare){
                if(chessBoard.getColumnIndex(node) == x && chessBoard.getRowIndex(node) == y)
                    return (ChessSquare) node;
            }
        }

        return null;
    }
    public ChessSquare getSquare(Piece piece){
        int pieceX = piece.getX();
        int pieceY = piece.getY();

        for(Node node : chessBoard.getChildren()){
            if(node instanceof ChessSquare){
                if(pieceX == ((ChessSquare) node).getX() && pieceY == ((ChessSquare) node).getY()){
                    return (ChessSquare) node;
                }
            }
        }
        return null;
    }

    @FXML
    public void resetGame(){
        chessBoard.getChildren().clear();
        paintBoard();
        setupPieces();
        setListenersFor(Color.WHITE);
        System.out.println("game is reset");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chessBoard.getChildren().remove(chessBoard.getChildren());
        paintBoard();
        setupPieces();
        whiteTurn = true;
        setListenersFor(Color.WHITE);
        System.out.println("Listeners for WHITE set");

    }

    public GridPane getChessBoard() {
        return chessBoard;
    }

    public void highlightPossibleMoves(ArrayList<Move> moves) {
        for(Node node: chessBoard.getChildren()){
                for(Move move : moves){
                    if(node instanceof ChessSquare)
                        ((ChessSquare) node).highlight(move);
                    if(node instanceof Piece)
                        ((Piece) node).highlight(move);
            }
        }
    }

    public void dehighlightAllMoves(){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof ChessSquare){
                ((ChessSquare) node).dehighlight();
                ((ChessSquare) node).setDefaultListener();
            }
            if(node instanceof Piece){
                ((Piece) node).dehighlight();
            }
        }

    }
    public void setHighlightOnlyListeners(Piece pickedPiece){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof ChessSquare){
                if(!((ChessSquare) node).isHighlighted()){
                    ((ChessSquare) node).removeListeners();
                }
                else{
                    ((ChessSquare) node).receivePiece(pickedPiece);
                }
            }
            if(node instanceof Piece){
                if(node.equals(pickedPiece)) {
                    ((Piece) node).setDeselectListener();
                   }
                if(!node.equals(pickedPiece) && !((Piece) node).isHighlighted()){
                    ((Piece) node).removeListeners();
                }

                if(((Piece) node).isHighlighted()){
                    ((Piece) node).setUnderTreatListener(pickedPiece);
                }

            }

        }
    }

    @FXML
    public void highlightBlackMoves(){
        if(!clicked){

            highlightPossibleMoves(getPossibleMovesForAll(Color.BLACK));
        }else
        {
            dehighlightAllMoves();
            clicked = true;
        }
    }


    public ArrayList<Move> getPossibleMovesForAll(Color color){
        ArrayList<Move> moves = new ArrayList<>();
        for(Node node : chessBoard.getChildren()){
            if(node instanceof Piece && ((Piece) node).getColor().equals(color)){
                moves.addAll(((Piece) node).getAvailableMoves());
            }
        }


        return moves;
    }

    public boolean isCheck(Color color){
        for(Move move : getPossibleMovesForAll(color)){
            if(move.getNewX() == getWhiteKingX() && move.getNewY() == getBlackKingX()){
                return true;
            }
        }
        return false;
    }

    public int getBlackKingY() {
        return blackKingY;
    }

    public void setBlackKingY(int blackKingY) {
        this.blackKingY = blackKingY;
    }

    public int getBlackKingX() {
        return blackKingX;
    }

    public void setBlackKingX(int blackKingX) {
        this.blackKingX = blackKingX;
    }

    public int getWhiteKingY() {
        return whiteKingY;
    }

    public void setWhiteKingY(int whiteKingY) {
        this.whiteKingY = whiteKingY;
    }

    public int getWhiteKingX() {
        return whiteKingX;
    }

    public void setWhiteKingX(int whiteKingX) {
        this.whiteKingX = whiteKingX;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public void setListenersFor(Color color){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof Piece && ((Piece) node).getColor().equals(color)){
                ((Piece) node).setDefaultListener();
            }
            if(node instanceof Piece && !((Piece) node).getColor().equals(color)){
                ((Piece) node).removeListeners();
            }
        }
    }
}

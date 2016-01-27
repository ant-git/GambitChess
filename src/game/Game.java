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
public class Game implements Initializable{

    @FXML
    GridPane chessBoard;

    public Game() {


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
            getSquare(i,1).setPiece(pawn);
        }

        for(int i=0; i < 8; i++){
            Pawn pawn = new Pawn(i,6,Color.WHITE, this);
            getSquare(i,6).setPiece(pawn);
        }

        King bking = new King(4,0, Color.BLACK, this);
        getSquare(4,0).setPiece(bking);

        King wking = new King(4,7, Color.WHITE, this);
        getSquare(4,7).setPiece(wking);

        Rook brook1 = new Rook(0,0,Color.BLACK, this);
        getSquare(0,0).setPiece(brook1);
        Rook brook2 = new Rook(7,0,Color.BLACK, this);
        getSquare(7,0).setPiece(brook2);
        Rook wrook1 = new Rook(0,7,Color.WHITE, this);
        getSquare(0,7).setPiece(wrook1);
        Rook wrook2 = new Rook(7,7,Color.WHITE, this);
        getSquare(7,7).setPiece(wrook2);

        Queen bqueen = new Queen(3,0,Color.BLACK, this);
        getSquare(3,0).setPiece(bqueen);
        Queen wqueen = new Queen(3,7,Color.WHITE, this);
        getSquare(3,7).setPiece(wqueen);

        Bishop bbishop1 = new Bishop(2,0,Color.BLACK, this);
        getSquare(2,0).setPiece(bbishop1);
        Bishop bbishop2 = new Bishop(5,0,Color.BLACK, this);
        getSquare(5,0).setPiece(bbishop2);
        Bishop wbishop1 = new Bishop(2,7,Color.WHITE, this);
        getSquare(2,7).setPiece(wbishop1);
        Bishop wbishop2 = new Bishop(5,7,Color.WHITE, this);
        getSquare(5,7).setPiece(wbishop2);

        Knight bknight1 = new Knight(1,0,Color.BLACK, this);
        getSquare(1,0).setPiece(bknight1);
        Knight bknight2 = new Knight(6,0,Color.BLACK, this);
        getSquare(6,0).setPiece(bknight2);
        Knight wknight1 = new Knight(1,7,Color.WHITE, this);
        getSquare(1,7).setPiece(wknight1);
        Knight wknight2 = new Knight(6,7,Color.WHITE, this);
        getSquare(6,7).setPiece(wknight2);

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
        setListenersFor(Color.WHITE);

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



    public ArrayList<Move> getPossibleMovesForAll(Color color){
        ArrayList<Move> moves = new ArrayList<>();
        for(Node node : chessBoard.getChildren()){
            if(node instanceof Piece && ((Piece) node).getColor().equals(color)){
                moves.addAll(((Piece) node).getAvailableMoves());
            }
        }


        return moves;
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


    public Piece getKing(Color color){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof King && ((King) node).getColor().equals(color)){
               return (Piece) node;
            }
        }

        return null;
    }

    public boolean isKingUnderTreat(Color color){
        Color enemyColor = Color.WHITE;
        if(color.equals(Color.WHITE))
            enemyColor = Color.BLACK;
        boolean underTreat = false;
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(getPossibleMovesForAll(enemyColor));
        for(Move move : moves){
            if(move.getNewX() == getKing(color).getX() && move.getNewY() == getKing(color).getY()){
                underTreat = true;
            }
        }

        return underTreat;
    }




}

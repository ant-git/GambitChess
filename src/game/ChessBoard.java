package game;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pieces.*;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by antant on 21/01/16.
 */
public class ChessBoard  implements Initializable{

    @FXML
    GridPane chessBoard;

    public ChessBoard() {


    }

    public void paintBoard(){
        boolean white = true;
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                ChessSquare square;
                if(white) {
                    square = new ChessSquare(j,i,Color.WHITE);
                    chessBoard.add(square,j,i);

                    white = false;
                }else{
                    square = new ChessSquare(j,i,Color.BLACK);
                    chessBoard.add(square,j,i);
                    white = true;
                }
                if(j== 7){
                    white = !white;
                }

                square.setOnMouseClicked(event -> {
                    System.out.println();
                    System.out.println(square.getX() + " " + square.getY());
                    System.out.println(chessBoard.getChildren().indexOf(square));
                    System.out.println();
                    System.out.println(chessBoard.getRowIndex(square) + " " + chessBoard.getColumnIndex(square));
                });

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

        King bking = new King(4,0, Color.BLACK);
        chessBoard.add(bking, 4,0);
        bking.setOnMouseClicked(event -> System.out.println("BLACK KING CLICKED"));

        King wking = new King(4,7, Color.WHITE);
        chessBoard.add(wking, 4,7);
        wking.setOnMouseClicked(event -> System.out.println("KING CLICKED"));

        Rook brook1 = new Rook(0,0,Color.BLACK);
        chessBoard.add(brook1, 0,0);
        brook1.setOnMouseClicked(event -> System.out.println("BLACK ROOK CLICKED"));

        Rook brook2 = new Rook(7,0,Color.BLACK);
        chessBoard.add(brook2, 7,0);
        brook2.setOnMouseClicked(event -> System.out.println("BLACK ROOK CLICKED"));

        Rook wrook1 = new Rook(0,7,Color.WHITE);
        chessBoard.add(wrook1, 0,7);
        wrook1.setOnMouseClicked(event -> System.out.println("WHITE ROOK CLICKED"));

        Rook wrook2 = new Rook(7,7,Color.WHITE);
        chessBoard.add(wrook2, 7,7);
        wrook2.setOnMouseClicked(event -> System.out.println("WHITE ROOK CLICKED"));

        Queen bqueen = new Queen(3,0,Color.BLACK);
        chessBoard.add(bqueen,3,0);
        bqueen.setOnMouseClicked(event -> System.out.println("BLACK QUEEN CLICKED"));

        Queen wqueen = new Queen(3,7,Color.WHITE);
        chessBoard.add(wqueen,3,7);
        wqueen.setOnMouseClicked(event -> System.out.println("WHITE QUEEN CLICKED"));

        Bishop bbishop1 = new Bishop(2,0,Color.BLACK);
        chessBoard.add(bbishop1,2,0);
        bbishop1.setOnMouseClicked(event -> System.out.println("BLACK BISHOP CLICKED"));

        Bishop bbishop2 = new Bishop(5,0,Color.BLACK);
        chessBoard.add(bbishop2,5,0);
        bbishop2.setOnMouseClicked(event -> System.out.println("BLACK BISHOP CLICKED"));

        Bishop wbishop1 = new Bishop(2,7,Color.WHITE);
        chessBoard.add(wbishop1,2,7);
        wbishop1.setOnMouseClicked(event -> System.out.println("WHITE BISHOP CLICKED"));

        Bishop wbishop2 = new Bishop(5,7,Color.WHITE);
        chessBoard.add(wbishop2,5,7);
        wbishop2.setOnMouseClicked(event -> System.out.println("WHITE BISHOP CLICKED"));

        Knight bknight1 = new Knight(1,0,Color.BLACK);
        chessBoard.add(bknight1,1,0);
        bknight1.setOnMouseClicked(event -> System.out.println("BLACK KNIGHT CLICKED"));

        Knight bknight2 = new Knight(6,0,Color.BLACK);
        chessBoard.add(bknight2,6,0);
        bknight2.setOnMouseClicked(event -> System.out.println("BLACK KNIGHT CLICKED"));

        Knight wknight1 = new Knight(1,7,Color.WHITE);
        chessBoard.add(wknight1,1,7);
        wknight1.setOnMouseClicked(event -> System.out.println("WHITE KNIGHT CLICKED"));

        Knight wknight2 = new Knight(6,7,Color.WHITE);
        chessBoard.add(wknight2,6,7);
        wknight2.setOnMouseClicked(event -> System.out.println("WHITE KNIGHT CLICKED"));


        // chessBoard.getChildren().remove(pawn); // *that's how you remove element from gridpane

    }

    public void checkEmptySquares(){
        chessBoard.getChildren().stream().filter(node -> node instanceof ChessSquare).forEach(node -> {
            System.out.println(((ChessSquare) node).isEmpty());
        });
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
        paintBoard();
        setupPieces();
        System.out.println("game is reset");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chessBoard.getChildren().remove(chessBoard.getChildren());
        paintBoard();
        setupPieces();

    }

    public GridPane getChessBoard() {
        return chessBoard;
    }
}

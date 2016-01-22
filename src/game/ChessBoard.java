package game;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import pieces.*;

import java.net.URL;
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
                    square = new ChessSquare(j,i,true);
                    chessBoard.add(square,j,i);

                    white = false;
                }else{
                    square = new ChessSquare(j,i,false);
                    chessBoard.add(square,j,i);
                    white = true;
                }
                if(j== 7){
                    white = !white;
                }

                square.setOnMouseClicked(event -> System.out.println(square.getX() + " " + square.getY()));
            }
        }

    }



    public  void setupPieces(){


        for(int i=0; i < 8; i++){
            Pawn pawn = new Pawn(false);
            chessBoard.add(pawn,i,1);
            pawn.setX(i);
            pawn.setY(1);
            pawn.setOnMouseClicked(event -> {
                System.out.println("BlLACK PAWN CLICKED @ " + pawn.getX() + " " + pawn.getY());
                chessBoard.getChildren().remove(pawn);
                chessBoard.add(pawn, pawn.getX(), pawn.getY()+1);
                pawn.setX(pawn.getX());
                pawn.setY(pawn.getY()+1);


            });
        }

        for(int i=0; i < 8; i++){
            Pawn pawn = new Pawn(true);
            chessBoard.add(pawn,i,6);
            pawn.setX(i);
            pawn.setY(6);
            pawn.setOnMouseClicked(event -> {
                System.out.println("WHITE PAWN CLICKED @ " + pawn.getX() + " " + pawn.getY());
                chessBoard.getChildren().remove(pawn);
                chessBoard.add(pawn, pawn.getX(), pawn.getY()-1);
                pawn.setX(pawn.getX());
                pawn.setY(pawn.getY()-1);


            });
        }

        King bking = new King(false);
        chessBoard.add(bking, 4,0);
        bking.setX(4);
        bking.setY(0);
        bking.setOnMouseClicked(event -> System.out.println("BLACK KING CLICKED"));

        King wking = new King(true);
        chessBoard.add(wking, 4,7);
        wking.setOnMouseClicked(event -> System.out.println("KING CLICKED"));

        Rook brook1 = new Rook(false);
        chessBoard.add(brook1, 0,0);
        brook1.setOnMouseClicked(event -> System.out.println("BLACK ROOK CLICKED"));

        Rook brook2 = new Rook(false);
        chessBoard.add(brook2, 7,0);
        brook2.setOnMouseClicked(event -> System.out.println("BLACK ROOK CLICKED"));

        Rook wrook1 = new Rook(true);
        chessBoard.add(wrook1, 0,7);
        wrook1.setOnMouseClicked(event -> System.out.println("WHITE ROOK CLICKED"));

        Rook wrook2 = new Rook(true);
        chessBoard.add(wrook2, 7,7);
        wrook2.setOnMouseClicked(event -> System.out.println("WHITE ROOK CLICKED"));

        Queen bqueen = new Queen(false);
        chessBoard.add(bqueen,3,0);
        bqueen.setOnMouseClicked(event -> System.out.println("BLACK QUEEN CLICKED"));

        Queen wqueen = new Queen(true);
        chessBoard.add(wqueen,3,7);
        wqueen.setOnMouseClicked(event -> System.out.println("WHITE QUEEN CLICKED"));

        Bishop bbishop1 = new Bishop(false);
        chessBoard.add(bbishop1,2,0);
        bbishop1.setOnMouseClicked(event -> System.out.println("BLACK BISHOP CLICKED"));

        Bishop bbishop2 = new Bishop(false);
        chessBoard.add(bbishop2,5,0);
        bbishop2.setOnMouseClicked(event -> System.out.println("BLACK BISHOP CLICKED"));

        Bishop wbishop1 = new Bishop(true);
        chessBoard.add(wbishop1,2,7);
        wbishop1.setOnMouseClicked(event -> System.out.println("WHITE BISHOP CLICKED"));

        Bishop wbishop2 = new Bishop(true);
        chessBoard.add(wbishop2,5,7);
        wbishop2.setOnMouseClicked(event -> System.out.println("WHITE BISHOP CLICKED"));

        Knight bknight1 = new Knight(false);
        chessBoard.add(bknight1,1,0);
        bknight1.setOnMouseClicked(event -> System.out.println("BLACK KNIGHT CLICKED"));

        Knight bknight2 = new Knight(false);
        chessBoard.add(bknight2,6,0);
        bknight2.setOnMouseClicked(event -> System.out.println("BLACK KNIGHT CLICKED"));

        Knight wknight1 = new Knight(true);
        chessBoard.add(wknight1,1,7);
        wknight1.setOnMouseClicked(event -> System.out.println("WHITE KNIGHT CLICKED"));

        Knight wknight2 = new Knight(true);
        chessBoard.add(wknight2,6,7);
        wknight2.setOnMouseClicked(event -> System.out.println("WHITE KNIGHT CLICKED"));


        // chessBoard.getChildren().remove(pawn); // *that's how you remove element from gridpane

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

}

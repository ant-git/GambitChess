package game;


import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import pieces.Pawn;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by antant on 21/01/16.
 */
public class ChessBoard  implements Initializable{

    ChessSquare[][] board = new ChessSquare[8][8];
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



    public  void setPieces(){
        int squareCounter = 0;
        int pawnCounter = 0;
        Pawn pawn = new Pawn(true);
        chessBoard.add(pawn, 3,3);
        for(Node node : chessBoard.getChildren()){
            if(node instanceof ChessSquare){
                squareCounter++;
                System.out.println("ChessSquare #" + squareCounter + "  x: " +((ChessSquare) node).getX() + "  y: " + ((ChessSquare) node).getY());
            }

            if(node instanceof Pawn){
                pawnCounter++;
                System.out.println("Pawn #" + pawnCounter + "  x: " +((Pawn) node).getX() + "  y: " + ((Pawn) node).getY());
            }
        }

        pawn.setOnMouseClicked(event -> System.out.println("PAWN CLICKED"));
        chessBoard.getChildren().remove(pawn);

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paintBoard();
        setPieces();
    }

}

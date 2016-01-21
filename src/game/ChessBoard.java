package game;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.*;
import pieces.Pawn;
import pieces.Piece;
import pieces.Rook;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static java.awt.Color.WHITE;

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
                if(white) {
                    chessBoard.add(new ChessSquare(j,i,true) ,j,i);
                    white = false;
                }else{
                    chessBoard.add(new ChessSquare(j,i,false) ,j,i);
                    white = true;
                }
                if(j== 7){
                    white = !white;
                }
            }
        }

    }

    public void drawBoard(){

    }



    public  void setPieces(){
        for(int i = 0; i < 8; i++){
            board[i][6].setPiece(new Pawn("white"));
        }
    }

    public void testRemovePieces(){
        for(int i = 0; i < 8; i++){
            board[i][6].removePiece();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paintBoard();
    }


    public Node getNodeByRowColumnIndex(final int row,final int column,GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for(Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }
}

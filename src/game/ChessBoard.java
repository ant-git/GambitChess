package game;


import pieces.Piece;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by antant on 21/01/16.
 */
public class ChessBoard {


    ChessSquare[][] board = new ChessSquare[8][8];
    public ChessBoard() {
        populateBoard();
        drawBoard();
        System.out.println();
        setPieces();
        drawBoard();
        System.out.println();
        testRemovePieces();
        drawBoard();
    }

    public void drawBoard(){
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                System.out.print(board[j][i].getColor());
                if(j == 7){
                    System.out.println();
                }
            }
        }
    }

    public void populateBoard(){
        boolean white = true;
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(white) {
                    board[i][j] = new ChessSquare(j, i, "w");
                    white = false;
                }else{
                    board[i][j] = new ChessSquare(j, i, "b");
                    white = true;
                }
                if(j== 7){
                    white = !white;
                }
            }
        }
    }

    public  void setPieces(){
        for(int i = 0; i < 8; i++){
            board[i][6].setPiece(new Piece());
        }
    }

    public void testRemovePieces(){
        for(int i = 0; i < 8; i++){
            board[i][6].removePiece();
        }
    }

}

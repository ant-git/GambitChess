package game;


import pieces.Pawn;
import pieces.Piece;
import pieces.Rook;

import java.awt.*;
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
        Pawn pawn = new Pawn("black");
        board[0][4].setPiece(pawn);
        System.out.println();
        drawBoard();
        System.out.println("Pawn x and y: " + pawn.getX() + " " + pawn.getY());
        System.out.println("Pawn color: " + pawn.getColor());

        Rook rook1 = new Rook("white");
        board[0][7].setPiece(rook1);
        Rook rook2 = new Rook("white");
        board[7][7].setPiece(rook2);
        System.out.println();
        drawBoard();
        System.out.println("rook x and y: " + rook1.getX() + " " + rook1.getY());
        System.out.println("rook color: " + rook1.getColor());

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
                    board[j][i] = new ChessSquare(j, i, "0");
                    white = false;
                }else{
                    board[j][i] = new ChessSquare(j, i, "*");
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
            board[i][6].setPiece(new Pawn("white"));
        }
    }

    public void testRemovePieces(){
        for(int i = 0; i < 8; i++){
            board[i][6].removePiece();
        }
    }

}

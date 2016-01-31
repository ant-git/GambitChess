package game;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import pieces.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Anton
 * Class to control the game and Controller for the main stage
 */
public class Game implements Initializable{

    @FXML
    private GridPane chessBoard;

    @FXML
    private HBox capturedWhites;
    @FXML
    private HBox capturedBlacks;
    @FXML
    private ChessSquare chessSquare;

    private ArrayList<Move> moves; // to keep history of all moves
    public Piece lastMovedPiece;


    public Game() {


    }

    //method to construct Chess Board
    public void paintBoard(){
        boolean white = true;
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(white) {
                    chessSquare = new ChessSquare(j,i,Color.WHITE, this);
                    chessBoard.add(chessSquare,j,i);
                    white = false;
                }else{
                    chessSquare = new ChessSquare(j,i,Color.BLACK, this);
                    chessBoard.add(chessSquare,j,i);
                    white = true;
                }
                if(j== 7){
                    white = !white;
                }

            }
        }

    }

    //Adding all the pieces to the chess board
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

    //getting Square by index
    public ChessSquare getSquare(int x, int y){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof ChessSquare){
                if(chessBoard.getColumnIndex(node) == x && chessBoard.getRowIndex(node) == y)
                    return (ChessSquare) node;
            }
        }

        return null;
    }

    //getting square by piece
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
        moves = new ArrayList<>();
        paintBoard();
        setupPieces();
        capturedWhites.getChildren().clear();
        capturedBlacks.getChildren().clear();
        setListenersFor(Color.WHITE);
        System.out.println("game is reset");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chessBoard.getChildren().clear();
        moves = new ArrayList<>();
        chessBoard.setMaxHeight(450);
        chessBoard.setMaxWidth(450);
        paintBoard();
        setupPieces();
        setListenersFor(Color.WHITE);

    }

    public GridPane getChessBoard() {
        return chessBoard;
    }

    //to highlight possible moves
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

    //to set listeners for highlighted pieces/squares only
    public void setHighlightOnlyListeners(Piece pickedPiece){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof ChessSquare){
                if(!((ChessSquare) node).isHighlighted()){
                    ((ChessSquare) node).removeListeners();
                }
                else{
                    ((ChessSquare) node).receivePieceListener(pickedPiece);
                }
            }
            if(node instanceof Piece){
                if(node.equals(pickedPiece)) {
                    ((Piece) node).setDeselectListener();
                   }
                if(!node.equals(pickedPiece) && !((Piece) node).isHighlighted()){
                    ((Piece) node).removeListener();
                }

                if(((Piece) node).isHighlighted()){
                    ((Piece) node).setUnderTreatListener(pickedPiece);
                }

            }

        }
    }


    //to get list of all possible moves for a particular color
    public ArrayList<Move> getPossibleMovesForAll(Color color){
        ArrayList<Move> moves = new ArrayList<>();
        for(Node node : chessBoard.getChildren()){
            if(node instanceof Piece && ((Piece) node).getColor().equals(color) && !(node instanceof King)){
                moves.addAll(((Piece) node).getAvailableMoves());
            }
        }


        return moves;
    }

    public void removeListeners(){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof Piece){
                ((Piece) node).removeListener();
            }
        }
    }

    public void setListenersFor(Color color){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof Piece && ((Piece) node).getColor().equals(color)){
                ((Piece) node).setDefaultListener();

            }
            if(node instanceof Piece && !((Piece) node).getColor().equals(color)){
                ((Piece) node).removeListener();
            }
        }
    }

    //to get Kin of a particular color
    public Piece getKing(Color color){
        for(Node node : chessBoard.getChildren()){
            if(node instanceof King && ((King) node).getColor().equals(color)){
               return (Piece) node;
            }
        }

        return null;
    }

    //to check if King of a particular color is under attack
    public boolean isKingUnderThreat(Color color){
        Color enemyColor = Color.WHITE;
        if(color.equals(Color.WHITE))
            enemyColor = Color.BLACK;
        boolean underTreat = false;
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(getPossibleMovesForAll(enemyColor));
        for(Move move : moves){
            if(move.getNewX() == getKing(color).getX()
                    && move.getNewY() == getKing(color).getY()
                    && !getPiece(move.getX(), move.getY()).getColor().equals(color)){
                underTreat = true;
            }
        }

        return underTreat;
    }

    //to get a count of all safe(check-checkmate free) moves for a particular color
    public int countSafeMovesFor(Color color){

        ArrayList<Piece> piecesToCheck = new ArrayList<>();
        for(Node node : chessBoard.getChildren()){
            if(node instanceof Piece && ((Piece) node).getColor().equals(color)){
                piecesToCheck.add((Piece) node);
            }
        }
        int counter = 0;
        for(Piece piece : piecesToCheck){
            counter += piece.filterMoves().size();
        }

        return counter;
    }

    //adding move to the history of moves
    public void addMoveToList(Move newMove){
        moves.add(newMove);

    }

    //to get piece from index
    public Piece getPiece(int x, int y){
        return getSquare(x,y).getPiece();
    }

    //to promote pawn to knight/queen/bishop or rook
    public void promotePawn(Piece pawn){
        int pawnX = pawn.getX();
        int pawnY = pawn.getY();
        Color pawnColor = pawn.getColor();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pawn is ready to be promoted");
        alert.setHeaderText("Choose a new piece:");


        ButtonType buttonKnight = new ButtonType("Knight");
        ButtonType buttonQueen = new ButtonType("Queen");
        ButtonType buttonBishop = new ButtonType("Bishop");
        ButtonType buttonRook = new ButtonType("Rook");

        alert.getButtonTypes().setAll(buttonKnight, buttonQueen, buttonBishop, buttonRook);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonKnight){
            pawn.remove();
            Knight knight = new Knight(pawnX, pawnY, pawnColor, this);
            getSquare(pawnX,pawnY).setPiece(knight);
        } else if (result.get() == buttonQueen) {
            pawn.remove();
            Queen queen = new Queen(pawnX, pawnY, pawnColor, this);
            getSquare(pawnX,pawnY).setPiece(queen);
        } else if (result.get() == buttonBishop) {
            pawn.remove();
            Bishop bishop = new Bishop(pawnX, pawnY, pawnColor, this);
            getSquare(pawnX,pawnY).setPiece(bishop);
        } else if (result.get() == buttonRook){
            pawn.remove();
            Rook rook = new Rook(pawnX, pawnY, pawnColor, this);
            getSquare(pawnX,pawnY).setPiece(rook);
        }
    }

    public Piece getLastMovedPiece() {
        return lastMovedPiece;
    }

    public void setLastMovedPiece(Piece lastMovedPiece) {
        this.lastMovedPiece = lastMovedPiece;
    }
    public boolean pieceIsWhiteAtIndex(int x, int y){
        return getSquare(x,y).getPiece().getColor().equals(Color.WHITE);
    }

    public void addToCapturedList(Piece piece){
        ImageView image = new ImageView(piece.getDefaultIconPath());
        image.setFitHeight(28);
        image.setFitWidth(28);

        if(piece.isWhite()) {

            capturedWhites.getChildren().add(image);
        }
        else {
            capturedBlacks.getChildren().add(image);
        }
    }


}

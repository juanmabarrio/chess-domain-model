package chess;

import chess.pieces.King;
import chess.pieces.Piece;
import utils.WithConsoleModel;

import java.util.ArrayList;
import java.util.List;

public class Game extends WithConsoleModel {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameStatus status;
    private List<Move> movesPlayed;


    public Game(){
        players = new Player[2];
        board = new Board();
        movesPlayed = new ArrayList<Move>();
        this.initialize(new Player(true) ,new Player(false));
    }
    private void initialize(Player p1, Player p2)
    {
        players[0] = p1;
        players[1] = p2;
        board.resetBoard();

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        }
        else {
            this.currentTurn = p2;
        }
        status = GameStatus.ACTIVE;
        movesPlayed.clear();
    }

    public boolean isEnd()
    {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public GameStatus getStatus()
    {
        return this.status;
    }

    public void setStatus(GameStatus status)
    {
        this.status = status;
    }

    public boolean playerMove(Player player, int startX,
                              int startY, int endX, int endY) throws Exception
    {
        Spot startBox = board.getBox(startX, startY);
        Spot endBox = board.getBox(endX, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player)
    {
        Piece sourcePiece = move.getStart().getPiece();
        if (sourcePiece == null) {
            return false;
        }

        // valid player
        if (player != currentTurn) {
            return false;
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        // valid move?
        if (!sourcePiece.canMove(board, move.getStart(),
                move.getEnd())) {
            return false;
        }

        // kill?
        Piece destPiece = move.getEnd().getPiece();
        if (destPiece != null) {
            destPiece.setKilled(true);
            move.setPieceKilled(destPiece);
        }

        // store the move
        movesPlayed.add(move);

        // move piece from the stat box to end box
        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        if (destPiece != null && destPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WINS);
            }
            else {
                this.setStatus(GameStatus.BLACK_WINS);
            }
        }
        // set the current turn to the other player
        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        }
        else {
            this.currentTurn = players[0];
        }

        return true;
    }

    private void play() {
        Message.TITLE.writeln();
        do{
            this.printBoard();
            if (this.currentTurn.isWhiteSide())
                Message.WHITE.writeln();
            else
                Message.BLACK.writeln();
            Message.CHOOSE_YOUR_MOVE.writeln();

            int pieceX = console.readInt("Enter X(vertical coordinate) of the piece you want to move:");
            int pieceY =console.readInt("Enter Y(horizontal coordinate) of the piece you want to move:");
            int destinationX =console.readInt("Enter X(vertical coordinate) of the destination box:") ;
            int destinationY =console.readInt("Enter Y(horizontal coordinate) of the destination box:") ;
            try {
                playerMove(currentTurn,pieceX,pieceY,destinationX,destinationY);
            } catch (Exception e) {
                console.write(Message.ILLEGAL_MOVE +":"+ e.getMessage());
            }
        }while (!isEnd());
        switch (this.getStatus())
        {
            case WHITE_WINS:
                Message.WHITE_WINS.writeln();
                break;
            case BLACK_WINS:
                Message.BLACK_WINS.writeln();
                break;
            case STALEMATE:
                Message.STALEMATE.writeln();
                break;
        }
    }

    private void printBoard(){

        for (int i = 0; i<8 ; i ++){
            for (int j=0 ; j<8 ; j++){
                try {
                    if (j == 0)
                        console.write(String.valueOf(i) + " ");
                    Piece piece = this.board.getBox(i,j).getPiece();
                    if (piece != null)
                        console.write(piece.getRepresentation());
                    else
                        console.write(" ");


                    console.write(" ");
                    if (j == 7)
                        console.writeln();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        console.write(" ");
        for (int i = 0; i<8 ; i ++){
            console.write(" ");
            console.write(String.valueOf(i));
        }
        console.writeln();
    }
    public static void main(String args[]){
        Game chessGame = new Game();
        chessGame.play();
    }


}


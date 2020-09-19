package chess.pieces;

import chess.Board;
import chess.Spot;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
        this.representation = 'P';
    }


    public boolean specificPieceCanMove(Board board, Spot start, Spot end) {
        if (this.isWhite() && (end.getX() - start.getX() != 1))
            return false;
        if (!this.isWhite() && (start.getX() - end.getX() != 1))
            return false;
        //when the pawn is not eating another piece, it should move on the same row
        if (end.getPiece()==null && Math.abs(end.getY() - start.getY()) != 0){
            return false;
        }
        //when the pawn is eating another piece, it should be just one column and one row away.
        //logic for the row is taken care at the beginning of this method... now is the column
        if (end.getPiece()!=null && Math.abs(end.getY() - start.getY()) != 1){
            return false;
        }

        return true;
    }

}

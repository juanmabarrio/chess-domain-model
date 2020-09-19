package chess.pieces;

import chess.Board;
import chess.Spot;

public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
        this.representation = 'R';
    }

    public boolean specificPieceCanMove(Board board, Spot start, Spot end) {

        //If is not moving in the same row or column, then return false:
        if (start.getX()!=end.getX() && start.getY()!=end.getY())
            return false;

        if (board.anyPieceBetween(start,end))
            return false;

        return true;
    }



}

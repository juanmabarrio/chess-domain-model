package chess.pieces;

import chess.Board;
import chess.Spot;

public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white);
        this.representation = 'B';
    }

    public boolean specificPieceCanMove(Board board, Spot start, Spot end) {
        //diagonal check
        if (!(Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY())))
            return false;
        if (board.anyPieceBetween(start,end))
            return false;

        return true;
    }

}

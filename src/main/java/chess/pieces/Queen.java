package chess.pieces;

import chess.Board;
import chess.Spot;

public class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
        this.representation = 'Q';
    }

    public boolean specificPieceCanMove(Board board, Spot start, Spot end) {
        //if the queen is not moving in diagonal...
        if (!(Math.abs(start.getX() - end.getX()) == Math.abs(start.getY() - end.getY()))) {
            //and is not moving in the same row or column, then return false:
            if (start.getX()!=end.getX() && start.getY()!=end.getY())
            return false;
        }
        if (board.anyPieceBetween(start,end))
            return false;
        return true;
    }


}

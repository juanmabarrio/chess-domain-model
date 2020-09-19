package chess.pieces;

import chess.Board;
import chess.Spot;

public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
        this.representation = 'k';
    }

    public boolean specificPieceCanMove(Board board, Spot start, Spot end) {
        if ((Math.abs(end.getX() - start.getX()) == 2) && (Math.abs(end.getY() - start.getY()) == 1))
            return true;
        if ((Math.abs(end.getY() - start.getY()) == 2) && (Math.abs(end.getX() - start.getX()) == 1))
            return true;
        return false;

    }

}

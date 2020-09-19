package chess.pieces;

import chess.Board;
import chess.Spot;

public class King extends Piece {

    public King(boolean white)
    {
        super(white);
        this.representation = 'K';
    }

    @Override
    public boolean specificPieceCanMove(Board board, Spot start, Spot end)
    {
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        //king moves only 1 position
        if (x + y == 1) {
            return true;
        }else {
            return false;
        }
    }

}

package chess.pieces;

import chess.Board;
import chess.Spot;

abstract public class Piece {
    private boolean killed = false;
    private boolean white = false;
    protected char representation;

    public Piece(boolean white)
    {
        this.setWhite(white);
    }

    public boolean isWhite()
    {
        return this.white == true;
    }

    public void setWhite(boolean white)
    {
        this.white = white;
    }

    public boolean isKilled()
    {
        return this.killed == true;
    }

    public void setKilled(boolean killed)
    {
        this.killed = killed;
    }

    public boolean canMove(Board board, Spot start, Spot end){
        // can't move the piece to a Spot that
        // has a piece of the same color
        if (end.getPiece()!=null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        //after the basic check, we let each specific piece decide if they can move or not
        return specificPieceCanMove(board, start, end);
    }
    public abstract boolean specificPieceCanMove(Board board,
                                    Spot start, Spot end);

     public char getRepresentation(){
         return this.representation;
     }
}

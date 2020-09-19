package chess;

import chess.pieces.Piece;

public class Spot {
    private Piece piece;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int x,y;

    public Spot(int x, int y, Piece piece){
        this.x = x;
        this.y = y;
        this.piece = piece;
    }
    public Spot(){}

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}

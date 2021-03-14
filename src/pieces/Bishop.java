package pieces;

import chess.Board;

public class Bishop extends Piece{

    public Bishop(char color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean move(Board board, int newRow, int newCol) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toString() {
        return getColor()+"B";
    }
    
}

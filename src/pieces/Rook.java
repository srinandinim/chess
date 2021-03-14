package pieces;

import chess.Board;

public class Rook extends Piece{

    public Rook(char color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean move(Board board, int newRow, int newCol) {

        if (newRow != getRow() && newCol != getCol())
            return false;

        
        
        return false;
    }

    @Override
    public String toString() {
        return getColor()+"R";
    }
    
}

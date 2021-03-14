package pieces;

import chess.Board;

public class Bishop extends Piece{

    public Bishop(char color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean move(Board board, int newRow, int newCol) {
    	if (newRow < 1 || newRow > board.getDimension()-1)
			return false;
		if (newCol < 1 || newCol > board.getDimension()-1)
			return false;
		
        return false;
    }

    @Override
    public String toString() {
        return getColor()+"B";
    }
    
}

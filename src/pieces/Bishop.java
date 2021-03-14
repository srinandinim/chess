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
		        
        if (Math.abs(newRow - getRow()) != Math.abs(newCol - getCol()))
            return false;

        if (board.getPiece(newRow, newCol) != null && board.getPiece(newRow, newCol).getColor() == getColor())
            return false;

        setCol(newCol);
        setRow(newRow);
        
        return true;
    }

    @Override
    public String toString() {
        return getColor()+"B";
    }
    
}

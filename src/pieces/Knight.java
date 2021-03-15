package pieces;

import chess.Board;

public class Knight extends Piece {

	public Knight(char color, char col, int row) {
        super(color, col, row);
    }
	
	@Override
	public boolean move(Board board, char newCol, int newRow) {
		if (newCol < 'a' || newCol > 'h')
            return false;
        if (newRow < 1 || newRow > board.getDimension())
			return false;
		
		int rowDist = Math.abs(getRow() - newRow);
		int colDist = Math.abs(getCol() - newCol);
		
		if ((rowDist != 2 && colDist != 1) && (rowDist != 1 && colDist != 2)) {
			return false;
		}
		
		if (board.getPiece(newCol, newRow) != null && (board.getPiece(newCol, newRow).getColor() == getColor())) {
			return false;
		}
		
		setRow(newRow);
		setCol(newCol);
			
		return true;
	}
	
	@Override
	public String toString() {
		return getColor() + "N";
	}
}

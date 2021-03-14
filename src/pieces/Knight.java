package pieces;

import chess.Board;

public class Knight extends Piece {

	public Knight(char color, int row, int col) {
		super(color, row, col);
	}
	
	@Override
	public boolean move(Board board, int newRow, int newCol) {
		
		int rowDist = Math.abs(getRow() - newRow);
		int colDist = Math.abs(getCol() - newCol);
		
		if ((rowDist != 2 && colDist != 1) && (rowDist != 1 && colDist != 2)) {
			return false;
		}
		
		if (board.getPiece(newRow, newCol) != null && (board.getPiece(newRow, newCol).getColor() == getColor())) {
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

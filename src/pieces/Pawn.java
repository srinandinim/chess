package pieces;

import chess.Board;

public class Pawn extends Piece{
	
	int initial;

	public Pawn(char color, int row, int col) {
		super(color, row, col);
		initial = 0;
	}

	@Override
	public boolean move(Board board, int newRow, int newCol) {
		if (newRow < 1 || newRow > board.getDimension()-1)
			return false;
		if (newCol < 1 || newCol > board.getDimension()-1)
			return false;
		
		initial = 1;
		return false;
	}

	@Override
	public String toString() {
		return getColor() + "p";
	}

}

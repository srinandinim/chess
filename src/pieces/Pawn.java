package pieces;

import chess.Board;

public class Pawn extends Piece{
	
	int initial;

	public Pawn(char color, char col, int row) {
		super(color, col, row);
		initial = 0;
	}

	@Override
	public boolean move(Board board, char newCol, int newRow) {
		if (newRow < 1 || newRow > board.getDimension()-1)
			return false;
		if (newCol < 'a' || newCol > 'h')
			return false;
		/*
		if (Math.abs(newCol - getCol()) > 1)
			return false;
		
		if (getColor() == 'w'){
			if (newRow <= getRow())
				return false;
		}
		
		if (getColor() == 'w' && getRow() == )
		
		*/
		initial = 1;
		return false;
	}

	@Override
	public String toString() {
		return getColor() + "p";
	}

}

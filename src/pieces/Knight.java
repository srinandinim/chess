package pieces;

import chess.Board;

public class Knight extends Piece {

	public Knight(String color, int row, int col) {
		super(color, row, col);
	}
	
	@Override
	public boolean move(Board board, int newRow, int newCol) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		if (isWhite())
			return "wN";
		return "bN";
	}
}

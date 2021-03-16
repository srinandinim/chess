package pieces;

import chess.Board;

public class Pawn extends Piece {

	public Pawn(char color, char col, int row) {
		super(color, col, row);
	}

	@Override
	public boolean canMove(Board board, char newCol, int newRow) {
		if (newRow < 1 || newRow > board.getDimension())
			return false;
		if (newCol < 'a' || newCol > 'h')
			return false;

		if (getColor() == 'w') {
			if (newCol == getCol()) {
				if (board.getPiece(newCol, getRow() + 1) != null)
					return false;
				if (getRow() == 2 && newRow == 4) {
					move(board, newCol, newRow);
					return true;
				}
				if (getRow() + 1 == newRow) {
					move(board, newCol, newRow);
					return true;
				}
			} else if (getRow() + 1 == newRow && (getCol() + 1 == newCol || getCol() - 1 == newCol)) {
				if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == 'b') {
					move(board, newCol, newRow);
					return true;
				}
			}
		}

		if (getColor() == 'b') {
			if (newCol == getCol()) {
				if (board.getPiece(newCol, getRow() - 1) != null)
					return false;
				if (getRow() == 7 && newRow == 5) {
					move(board, newCol, newRow);
					return true;
				}
				if (getRow() - 1 == newRow) {
					move(board, newCol, newRow);
					return true;
				}
			} else if (getRow() - 1 == newRow && (getCol() + 1 == newCol || getCol() - 1 == newCol)) {
				if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == 'w') {
					move(board, newCol, newRow);
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return getColor() + "p";
	}

}

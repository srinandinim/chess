package pieces;

import chess.Board;

public class Pawn extends Piece {

	boolean enpassantable;
	boolean enpassant;

	public Pawn(char color, char col, int row) {
		super(color, col, row);
		enpassantable = false;
		enpassant = false;
	}

	@Override
	public boolean canMove(Board board, char newCol, int newRow) {
		if (newRow < 1 || newRow > board.getDimension())
			return false;
		if (newCol < 'a' || newCol > 'h')
			return false;

		enpassantable = false;
		enpassant = false;

		if (getColor() == 'w') {
			if (newCol == getCol()) {
				if (board.getPiece(newCol, getRow() + 1) != null)
					return false;
				else if (getRow() == 2 && newRow == 4) {
					enpassantable = true;
					return true;
				} else if (getRow() + 1 == newRow)
					return true;
			} else if (getRow() + 1 == newRow && (getCol() + 1 == newCol || getCol() - 1 == newCol)) {
				if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == 'b')
					return true;
				if (board.getPiece(newCol, newRow) == null && board.getPiece(newCol, getRow()) instanceof Pawn) {
					if (board.getPiece(newCol, getRow()).getColor() == 'b'
							&& ((Pawn) board.getPiece(newCol, getRow())).getEnpassantable()) {
						enpassant = true;
						return true;
					}
				}
			}
		}

		if (getColor() == 'b') {
			if (newCol == getCol()) {
				if (board.getPiece(newCol, getRow() - 1) != null)
					return false;
				else if (getRow() == 7 && newRow == 5) {
					enpassantable = true;
					return true;
				} else if (getRow() - 1 == newRow)
					return true;
			} else if (getRow() - 1 == newRow && (getCol() + 1 == newCol || getCol() - 1 == newCol)) {
				if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == 'w')
					return true;
				if (board.getPiece(newCol, newRow) == null && board.getPiece(newCol, getRow()) instanceof Pawn) {
					if (board.getPiece(newCol, getRow()).getColor() == 'w'
							&& ((Pawn) board.getPiece(newCol, getRow())).getEnpassantable()) {
						enpassant = true;
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean getEnpassantable() {
		return enpassantable;
	}

	public void setEnpassantable(boolean enpassantable) {
		this.enpassantable = enpassantable;
	}

	public boolean getEnpassant() {
		return enpassant;
	}

	public void setEnpassant(boolean enpassant) {
		this.enpassant = enpassant;
	}

	@Override
	public void move(Board board, char newCol, int newRow){
		if (enpassant){
			board.nullLocation(newCol, getRow());
		}
		super.move(board, newCol, newRow);
	}

	@Override
	public String toString() {
		return getColor() + "p";
	}

}

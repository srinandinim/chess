package pieces;

import chess.Board;

/** 
 * Representation of a Pawn
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public class Pawn extends Piece {

	/** 
	 * Indicates whether the Pawn can engage in an En Passant
	 */
	boolean enpassantable;
	/** 
	 * Indicates whether the Pawn has engaged in an En Passant
	 */
	boolean enpassant;

	/** 
	 * Initalizes a Pawn
     * @see Piece
	 */
	public Pawn(char color, char col, int row) {
		super(color, col, row);
		enpassantable = false;
		enpassant = false;
	}

	
	/** 
	 * Checks if a Pawn is legally allowed to move to the input location based on the movement rules for a Pawn
	 * @param board Current representation of the board
	 * @param newCol Character of the new column to move to
	 * @param newRow Row number of the new row to move to
	 * @return boolean - true if input location is a valid piece, false otherwise 
	 */
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

	
	/** 
	 * Gets if Pawn is enpasasntable
	 * @return boolean - true if it is, false otherwise
	 */
	public boolean getEnpassantable() {
		return enpassantable;
	}

	
	/**
	 * Sets whether the Pawn is enpasasntable
	 * @param enpassantable Boolean indicative of it can engage in an en passant or not
	 */
	public void setEnpassantable(boolean enpassantable) {
		this.enpassantable = enpassantable;
	}

	
	/** 
	 * Gets if the Pawn engaged in an en passant
	 * @return boolean - true if it had, false otherwise
	 */
	public boolean getEnpassant() {
		return enpassant;
	}

	
	/** 
	 * Sets whether the Pawn engaged in an en passant
	 * @param enpassant Boolean indicative of if it engaged in an en passant or not 
	 */
	public void setEnpassant(boolean enpassant) {
		this.enpassant = enpassant;
	}

	
	/** 
	 * Moves the Pawn to the new location on the board and clears old location
	 * @param board Current representation of the board
	 * @param newCol Character of the new column to move to
	 * @param newRow Row number of the new row to move to
	 * @see Piece
	 */
	@Override
	public void move(Board board, char newCol, int newRow) {
		if (enpassant) {
			board.nullLocation(newCol, getRow());
		}
		super.move(board, newCol, newRow);
	}

	
	/** 
	 * Output string used for printing
	 * @return String - Output string
	 */
	@Override
	public String toString() {
		return getColor() + "p";
	}

}

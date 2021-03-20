package pieces;

import chess.Board;

/** 
 * Representation of a Knight
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public class Knight extends Piece {

	/** 
	 * Initalizes a Knight
     * @see Piece
	 */
	public Knight(char color, char col, int row) {
		super(color, col, row);
	}

	
	/** 
	 * Checks if a Knight is legally allowed to move to the input location based on the movement rules for a Knight
	 * @param board Current representation of the board
	 * @param newCol Character of the new column to move to
	 * @param newRow Row number of the new row to move to
	 * @return boolean - true if input location is a valid location, false otherwise 
	 */
	@Override
	public boolean canMove(Board board, char newCol, int newRow) {
		if (newCol < 'a' || newCol > 'h')
			return false;
		if (newRow < 1 || newRow > board.getDimension())
			return false;

		int rowDist = Math.abs(getRow() - newRow);
		int colDist = Math.abs(getCol() - newCol);

		if (!((rowDist == 2 && colDist == 1) || (rowDist == 1 && colDist == 2))) {
			return false;
		}

		if (board.getPiece(newCol, newRow) != null && (board.getPiece(newCol, newRow).getColor() == getColor())) {
			return false;
		}

		return true;
	}

	
	/** 
	 * Output string used for printing
	 * @return String - Output string
	 */
	@Override
	public String toString() {
		return getColor() + "N";
	}
	
}

package pieces;

import chess.Board;

/** 
 * Abstract super class for all pieces
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public abstract class Piece {

	/** 
	 * Color of the piece ('w'/'b')
	 */
	private char color;

	/** 
	 * Represents the column of the piece
	 */
	private char col;

	/** 
	 * Represents the row number of the piece
	 */
	private int row;

	/** 
	 * Super constructor for all pieces
	 * @param color 
	 * @param row
	 * @param col
	 */
	public Piece(char color, char col, int row) {
		this.color = color;
		this.row = row;
		this.col = col;
	}

	
	/** 
	 * Checks if a pieces is legally allowed to move to the input location based on the movement rules of each piece
	 * @param board Current representation of the board
	 * @param newCol Character of the new column to move to
	 * @param newRow Row number of the new row to move to
	 * @return boolean - true if input location is a valid location, false otherwise 
	 */
	public abstract boolean canMove(Board board, char newCol, int newRow);

	
	/** 
	 * Output string used for printing
	 * @return String - Output string
	 */
	public abstract String toString();

	
	/** 
	 * Moves the piece to the new location on the board and clears old location
	 * @param board Current representation of the board
	 * @param newCol Character of the new column to move to
	 * @param newRow Row number of the new row to move to
	 */
	public void move(Board board, char newCol, int newRow) {
		board.nullLocation(getCol(), getRow());

		setCol(newCol);
		setRow(newRow);

		board.setPiece(this);
	}

	
	/** 
	 * Sets the color of a piece
	 * @param color New color of the piece ('w'/'b')
	 */
	public void setColor(char color) {
		this.color = color;
	}

	
	/** 
	 * Gets the color of the piece
	 * @return char - Color of the piece ('w'/'b')
	 */
	public char getColor() {
		return color;
	}

	
	/** 
	 * Sets the row number of the piece
	 * @param row New row to set the piece to
	 */
	public void setRow(int row) {
		this.row = row;
	}

	
	/** 
	 * Sets the column character of the piece
	 * @param col New column to set the piece to
	 */
	public void setCol(char col) {
		this.col = col;
	}

	
	/** 
	 * Gets the row number of the piece
	 * @return int - Current row number of the piece
	 */
	public int getRow() {
		return row;
	}

	
	/** 
	 * Gets the column character of the piece
	 * @return char - Current column character of the piece
	 */
	public char getCol() {
		return col;
	}
}

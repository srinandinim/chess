package pieces;

import chess.Board;

public abstract class Piece {

	private char color;
	private char col;
	private int row;

	public Piece(char color, char col, int row) {
		this.color = color;
		this.row = row;
		this.col = col;
	}

	
	/** 
	 * @param board
	 * @param newCol
	 * @param board
	 * @param newCol
	 * @param newRow
	 * @return boolean
	 */
	public abstract boolean canMove(Board board, char newCol, int newRow);

	
	/** 
	 * @param board
	 * @param newCol
	 * @param newRow
	 * @return String
	 */
	public abstract String toString();

	
	/** 
	 * @param board
	 * @param newCol
	 * @param newRow
	 */
	public void move(Board board, char newCol, int newRow) {
		board.nullLocation(getCol(), getRow());

		setCol(newCol);
		setRow(newRow);

		board.setPiece(this);
	}

	
	/** 
	 * @param color
	 */
	public void setColor(char color) {
		this.color = color;
	}

	
	/** 
	 * @return char
	 */
	public char getColor() {
		return color;
	}

	
	/** 
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	
	/** 
	 * @param col
	 */
	public void setCol(char col) {
		this.col = col;
	}

	
	/** 
	 * @return int
	 */
	public int getRow() {
		return row;
	}

	
	/** 
	 * @return char
	 */
	public char getCol() {
		return col;
	}
}

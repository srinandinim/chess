package pieces;

import chess.Board;

public abstract class Piece {

	private char color;
	private int row, col;

	public Piece(char color, int row, int col) {
		this.color = color;
		this.row = row;
		this.col = col;
	}

	public abstract boolean move(Board board, int newRow, int newCol);
	
	public abstract String toString();
	
	public void setColor(char color) {
		this.color = color;
	}

	public char getColor(){
		return color;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}

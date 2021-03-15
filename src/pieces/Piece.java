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

	public abstract boolean move(Board board, char newCol, int newRow);
	
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
	
	public void setCol(char col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public char getCol() {
		return col;
	}
}

package pieces;

import chess.Board;

public abstract class Piece {

	private boolean white;
	private int row, col;

	public Piece(String color, int row, int col) {
		if (color.toLowerCase().equals("white")) {
			white = true;
		} else {
			white = false;
		}
		this.row = row;
		this.col = col;
	}

	public abstract boolean move(Board board, int newRow, int newCol);
	
	public abstract String toString();
	
	public void getWhite(boolean white) {
		this.white = white;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public boolean isWhite() {
		return white;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}

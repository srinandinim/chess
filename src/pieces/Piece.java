package pieces;

public abstract class Piece {

	private boolean white;
	private int row;
	private int col;

	public Piece(String color, int row, int col) {
		if (color.equals("white")) {
			white = true;
		} else {
			white = false;
		}
		this.row = row;
		this.col = col;
	}

	public abstract boolean move (int newRow, int newCol);

	public abstract String toString();

	public boolean getWhite() {
		return white;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}

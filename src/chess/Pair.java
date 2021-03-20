package chess;

import pieces.Piece;

public class Pair {

	private boolean bool;
	private Piece piece;

	public Pair(boolean bool, Piece piece) {
		this.bool = bool;
		this.piece = piece;
	}

	public boolean getBool() {
		return bool;
	}

	public Piece getPiece() {
		return piece;
	}
}

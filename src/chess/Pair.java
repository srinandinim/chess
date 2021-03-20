package chess;

import pieces.Piece;

public class Pair {

	private boolean bool;
	private Piece piece;

	public Pair(boolean bool, Piece piece) {
		this.bool = bool;
		this.piece = piece;
	}

	
	/** 
	 * @return boolean
	 */
	public boolean getBool() {
		return bool;
	}

	
	/** 
	 * @return Piece
	 */
	public Piece getPiece() {
		return piece;
	}
}

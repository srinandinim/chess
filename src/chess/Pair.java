package chess;

import pieces.Piece;

/** 
 * Represents output for check
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public class Pair {

	/** 
	 * Holds whether opppsing side is in check
	 */
	private boolean bool;

	/** 
	 * Holds the piece that causes opponent to be in check
	 */
	private Piece piece;

	/** 
	 * Initalizes Pair
	 * @param bool Boolean input
	 * @param piece Piece causing check
	 */
	public Pair(boolean bool, Piece piece) {
		this.bool = bool;
		this.piece = piece;
	}

	
	/** 
	 * Gets bool
	 * @return boolean - true if bool is true, false otherwise
	 */
	public boolean getBool() {
		return bool;
	}

	
	/** 
	 * Gets piece
	 * @return Piece - Piece that causes check
	 */
	public Piece getPiece() {
		return piece;
	}
}

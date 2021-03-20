package pieces;

import chess.Board;

/** 
 * Representation of a Queen
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public class Queen extends Piece {

    /** 
	 * Initalizes a Queen
     * @see Piece
	 */
    public Queen(char color, char col, int row) {
        super(color, col, row);
    }

    
	/** 
	 * Checks if a Queen is legally allowed to move to the input location based on the movement rules for a Queen
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

        if (Math.abs(newRow - getRow()) != Math.abs(newCol - getCol()) && (newRow != getRow() && newCol != getCol()))
            return false;

        if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == getColor())
            return false;

        if (Math.abs(newRow - getRow()) == Math.abs(newCol - getCol())) {
            if ((getRow() - newRow) == (getCol() - newCol)) {
                if (getRow() - newRow < 0) {
                    for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++) {
                        if (board.getPiece((char) (getCol() + (i - Math.min(getRow(), newRow))), i) != null)
                            return false;
                    }
                } else {
                    for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++) {
                        if (board.getPiece((char) (getCol() - (Math.max(getRow(), newRow)) + i), i) != null)
                            return false;
                    }
                }
            } else {
                if (getRow() - newRow < 0) {
                    for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++) {
                        if (board.getPiece((char) (getCol() - (i - Math.min(getRow(), newRow))), i) != null)
                            return false;
                    }
                } else {
                    for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++) {
                        if (board.getPiece((char) (getCol() + (Math.max(getRow(), newRow)) - i), i) != null)
                            return false;
                    }
                }
            }
        } else {
            if (newRow == getRow()) {
                for (int i = Math.min(getCol(), newCol) + 1; i < Math.max(getCol(), newCol); i++) {
                    if (board.getPiece((char) i, newRow) != null)
                        return false;
                }
            } else if (newCol == getCol()) {
                for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++) {
                    if (board.getPiece(newCol, i) != null)
                        return false;
                }
            }
        }

        return true;
    }

    
	/** 
	 * Output string used for printing
	 * @return String - Output string
	 */
    @Override
    public String toString() {
        return getColor() + "Q";
    }

}

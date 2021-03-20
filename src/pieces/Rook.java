package pieces;

import chess.Board;

/** 
 * Representation of the Rook
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public class Rook extends Piece {

    /** 
	 * Indicates whether the Rook has moved yet, therby impacting its ability to castle
	 */
    private boolean canCastle;

    /** 
	 * Initalizes a Rook
     * @see Piece#Piece(char, char, int)
	 */
    public Rook(char color, char col, int row) {
        super(color, col, row);
        canCastle = true;
    }

    
	/** 
	 * Checks if a Rook is legally allowed to move to the input location based on the movement rules for a Rook
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

        if (newRow != getRow() && newCol != getCol())
            return false;

        if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == getColor())
            return false;

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

        return true;
    }

    
	/** 
	 * Output string used for printing
	 * @return String - Output string
	 */
    @Override
    public String toString() {
        return getColor() + "R";
    }

    
    /** 
     * Gets whether the Rook can castle
     * @return boolean - true if it can castle, false otherwise
     */
    public boolean getCanCastle() {
        return canCastle;
    }

    
    /** 
     * Sets whether the Rook can castle
     * @param canCastle Boolean indicative of if it can castle or not
     */
    public void setCanCastle(boolean canCastle) {
        this.canCastle = canCastle;
    }

    
	/** 
	 * Moves the Rook to the new location on the board and clears old location
	 * @param board Current representation of the board
	 * @param newCol Character of the new column to move to
	 * @param newRow Row number of the new row to move to
	 * @see Piece
	 */
    @Override
    public void move(Board board, char newCol, int newRow) {
        canCastle = false;
        super.move(board, newCol, newRow);
    }

}

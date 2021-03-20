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
	 * Initializes the Rook
     * @param color Color of the Rook ('w'/'b')
     * @param col Character representing the column of its initial position
     * @param row Row number of its initial position
	 */
    public Rook(char color, char col, int row) {
        super(color, col, row);
        canCastle = true;
    }

    
    /** 
     * @param board
     * @param newCol
     * @param newRow
     * @return boolean
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
     * @return String
     */
    @Override
    public String toString() {
        return getColor() + "R";
    }

    
    /** 
     * Returns whether the Rook can castle
     * @return boolean - true if it can castle, false if it cannot
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
     * @param board
     * @param newCol
     * @param newRow
     */
    @Override
    public void move(Board board, char newCol, int newRow) {
        canCastle = false;
        super.move(board, newCol, newRow);
    }

}

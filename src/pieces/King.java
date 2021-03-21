package pieces;

import chess.*;

/** 
 * Representation of a King
 * @author Swapnil Napuri
 * @author Srinandini Marpaka
 */

public class King extends Piece {

    /** 
	 * Indicates whether the King has moved yet, therby impacting its ability to castle
	 */
    private boolean canCastle;

    /** 
	 * Initalizes a King
     * @param color Color of the piece
	 * @param row Starting row number 
	 * @param col Starting column character
	 */
    public King(char color, char col, int row) {
        super(color, col, row);
        canCastle = true;
    }

    
	/** 
	 * Checks if a King is legally allowed to move to the input location based on the movement rules for a King
	 * @param board Current representation of the board
	 * @param newCol Character of the new column to move to
	 * @param newRow Row number of the new row to move to
	 * @return boolean - true if input location is a valid piece, false otherwise 
	 */
    @Override
    public boolean canMove(Board board, char newCol, int newRow) {

        if (newCol < 'a' || newCol > 'h')
            return false;
        if (newRow < 1 || newRow > board.getDimension())
            return false;

        if (!Chess.causesCheck(board, getColor(), getCol(), getRow()).getBool() && canCastle && newCol == 'g'
                && newRow == getRow()) {
            if (board.getPiece('f', getRow()) == null && board.getPiece('g', getRow()) == null) {
                if (board.getPiece('h', getRow()) instanceof Rook
                        && ((Rook) board.getPiece('h', getRow())).getCanCastle()) {
                    if (!Chess.causesCheck(board, getColor(), 'f', getRow()).getBool()) {
                        ((Rook) board.getPiece('h', getRow())).setCanCastle(false);
                        board.getPiece('h', getRow()).move(board, 'f', getRow());
                        canCastle = false;
                        return true;
                    }
                }
            }
        }

        if (!Chess.causesCheck(board, getColor(), getCol(), getRow()).getBool() && canCastle && newCol == 'c'
                && newRow == getRow()) {
            if (board.getPiece('b', getRow()) == null && board.getPiece('c', getRow()) == null
                    && board.getPiece('d', getRow()) == null) {
                if (board.getPiece('a', getRow()) instanceof Rook
                        && ((Rook) board.getPiece('a', getRow())).getCanCastle()) {
                    if (!Chess.causesCheck(board, getColor(), 'd', getRow()).getBool()) {
                        ((Rook) board.getPiece('a', getRow())).setCanCastle(false);
                        board.getPiece('a', getRow()).move(board, 'd', getRow());
                        canCastle = false;
                        return true;
                    }
                }
            }
        }

        if (Math.abs(newCol - getCol()) > 1 || Math.abs(newRow - getRow()) > 1)
            return false;

        if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == getColor())
            return false;

        return true;
    }

    
    /** 
     * Moves the King to the new location on the board and clears old location
     * @param board Current representation of the board
	 * @param newCol Character of the new column to move to
	 * @param newRow Row number of the new row to move to
     */
    @Override
    public void move(Board board, char newCol, int newRow) {
        canCastle = false;
        super.move(board, newCol, newRow);
    }


	/** 
	 * Output string used for printing
	 * @return String - Output string
	 */
    @Override
    public String toString() {
        return getColor() + "K";
    }

}

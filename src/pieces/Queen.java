package pieces;

import chess.Board;

public class Queen extends Piece {

    public Queen(char color, char col, int row) {
        super(color, col, row);
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
     * @return String
     */
    @Override
    public String toString() {
        return getColor() + "Q";
    }

}

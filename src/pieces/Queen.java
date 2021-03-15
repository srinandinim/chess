package pieces;

import chess.Board;

public class Queen extends Piece {

    public Queen(char color, char col, int row) {
        super(color, col, row);
    }

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
        
        if (Math.abs(newRow - getRow()) == Math.abs(newCol - getCol())){
            if ((getRow() - newRow) == (getCol() - newCol)){
                for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++){
                    if (board.getPiece((char) (i + getCol()), i) != null) 
                        return false;
                }
            } else {
                for (int i = Math.min(getCol(), newCol) + 1; i < Math.max(getCol(), newCol); i++){
                    if (board.getPiece((char) i, getRow() - (i - Math.min(getCol(), newCol))) != null) 
                        return false;
                }
            }
        } else {
            if (newRow == getRow()){
                for (int i = Math.min(getCol(), newCol) + 1; i < Math.max(getCol(), newCol); i++){
                    if (board.getPiece((char) i, newRow) != null) 
                        return false;
                }
            } else if (newCol == getCol()){
                for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++){
                    if (board.getPiece(newCol, i) != null) 
                        return false;
                }
            }
        }

        move(board, newCol, newRow);

        return true;
    }

    @Override
    public String toString() {
        return getColor() + "Q";
    }

}

package pieces;

import chess.Board;

public class Bishop extends Piece {

    public Bishop(char color, char col, int row) {
        super(color, col, row);
    }

    @Override
    public boolean canMove(Board board, char newCol, int newRow) {
        if (newCol < 'a' || newCol > 'h')
            return false;
        if (newRow < 1 || newRow > board.getDimension())
            return false;

        if (Math.abs(newRow - getRow()) != Math.abs(newCol - getCol()))
            return false;

        if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == getColor())
            return false;


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

        move(board, newCol, newRow);

        return true;
    }

    @Override
    public String toString() {
        return getColor() + "B";
    }

}

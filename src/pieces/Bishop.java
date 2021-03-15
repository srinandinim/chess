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
            if (getRow() - newRow < 0){
                for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++){
                    if (board.getPiece((char) (getCol() + (i - Math.min(getRow(), newRow))), i) != null) 
                        return false;
                }
            } else {
                for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++){
                    if (board.getPiece((char) (getCol() - (Math.max(getRow(), newRow)) + i), i) != null) 
                        return false;
                }
            }
        } else {
            if (getRow() - newRow < 0){
                for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++){
                    if (board.getPiece((char) (getCol() - (i - Math.min(getRow(), newRow))), i) != null) 
                        return false;
                }
            } else {
                for (int i = Math.min(getRow(), newRow) + 1; i < Math.max(getRow(), newRow); i++){
                    if (board.getPiece((char) (getCol() + (Math.max(getRow(), newRow)) - i), i) != null) 
                        return false;
                }
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

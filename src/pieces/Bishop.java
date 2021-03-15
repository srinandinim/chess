package pieces;

import chess.Board;

public class Bishop extends Piece {

    public Bishop(char color, char col, int row) {
        super(color, col, row);
    }

    @Override
    public boolean move(Board board, char newCol, int newRow) {
        if (newCol < 'a' || newCol > 'h')
            return false;
        if (newRow < 1 || newRow > board.getDimension())
            return false;

        if (Math.abs(newRow - getRow()) != Math.abs(newCol - getCol()))
            return false;

        if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == getColor())
            return false;

        setCol(newCol);
        setRow(newRow);

        return true;
    }

    @Override
    public String toString() {
        return getColor() + "B";
    }

}

package pieces;

import chess.*;

public class King extends Piece {

    private boolean canCastle;

    public King(char color, char col, int row) {
        super(color, col, row);
        canCastle = true;
    }

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

    @Override
    public String toString() {
        return getColor() + "K";
    }

    @Override
    public void move(Board board, char newCol, int newRow) {
        canCastle = false;
        super.move(board, newCol, newRow);
    }

}

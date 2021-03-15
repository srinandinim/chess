package pieces;

import chess.Board;

public class King extends Piece{

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

        if (canCastle && newCol == 'g'){
        }

        if (Math.abs(newCol - getCol()) > 1 || Math.abs(newRow - getRow()) > 1)
            return false;

        if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == getColor())
            return false;        

        canCastle = false;
        move(board, newCol, newRow);

        return false;
    }

    @Override
    public String toString() {
        return getColor() + "K";
    }
    
}

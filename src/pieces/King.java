package pieces;

import chess.*;

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

        if (!Chess.causesCheck(this, board, getCol(), getRow()) && canCastle && newCol == 'g' && newRow == getRow()){
            if (board.getPiece('f', getRow()) == null && board.getPiece('g', getRow()) == null){
                if (board.getPiece('h', getRow()) instanceof Rook && ((Rook)board.getPiece('h', getRow())).getCanCastle()){
                    if (!Chess.causesCheck(this, board, 'f', getRow())){
                        canCastle = false;
                        move(board, newCol, newRow);
                        return true;
                    }

                }
            }
        }

        if (!Chess.causesCheck(this, board, getCol(), getRow()) && canCastle && newCol == 'c' && newRow == getRow()){
            if (board.getPiece('b', getRow()) == null && board.getPiece('c', getRow()) == null && board.getPiece('d', getRow()) == null){
                if (board.getPiece('a', getRow()) instanceof Rook && ((Rook)board.getPiece('a', getRow())).getCanCastle()){
                    if (!Chess.causesCheck(this, board, 'd', getRow())){
                        canCastle = false;
                        move(board, newCol, newRow);
                        return true;
                    }
                }
            }
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

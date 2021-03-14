package pieces;

import chess.Board;

public class Rook extends Piece{

    public Rook(char color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public boolean move(Board board, int newRow, int newCol) {

        if (newRow != getRow() && newCol != getCol())
            return false; //invalid rook move

        if (board.getPiece(newRow, newCol) != null && board.getPiece(newRow, newCol).getColor() == getColor())
            return false;

        setCol(newCol);
        setRow(newRow);
        
        return true;
    }

    @Override
    public String toString() {
        return getColor()+"R";
    }
    
}

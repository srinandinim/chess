package pieces;

import chess.Board;

public class Rook extends Piece {

    private boolean canCastle;

    public Rook(char color, char col, int row) {
        super(color, col, row);
        canCastle = true;
    }

    @Override
    public boolean canMove(Board board, char newCol, int newRow) {
        if (newCol < 'a' || newCol > 'h')
            return false;
        if (newRow < 1 || newRow > board.getDimension())
            return false;

        if (newRow != getRow() && newCol != getCol())
            return false;

        if (board.getPiece(newCol, newRow) != null && board.getPiece(newCol, newRow).getColor() == getColor())
            return false;

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

        return true;
    }

    @Override
    public String toString() {
        return getColor() + "R";
    }

    public boolean getCanCastle(){
        return canCastle;
    }

    public void setCanCastle(boolean canCastle){
        this.canCastle = canCastle;
    }

    @Override
    public void move(Board board, char newCol, int newRow){
        canCastle = false;
        super.move(board, newCol, newRow);
    }

}

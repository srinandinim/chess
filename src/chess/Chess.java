package chess;

import pieces.Piece;
import pieces.Rook;

public class Chess {

	public static void main(String[] args) {
		Board board = new Board();
		// board.printBoard();

		Piece rook1 = new Rook('w', 'a', 1);
		board.setPiece(rook1);
		Piece rook2 = new Rook('b', 'd', 1);
		board.setPiece(rook2);
		System.out.println (rook1.canMove(board, 'c', 1));
		board.printBoard();
		System.out.println (rook1.canMove(board, 'b', 1));
		board.printBoard();
		System.out.println (rook1.canMove(board, 'd', 1));
		board.printBoard();
		System.out.println (rook1.canMove(board, 'h', 1));
		board.printBoard();
	}

}

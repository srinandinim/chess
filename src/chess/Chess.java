package chess;

import pieces.Piece;
import pieces.Bishop;
import pieces.Rook;

public class Chess {

	public static void main(String[] args) {
		Board board = new Board();
		// board.printBoard();
		/*
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
		*/

		Piece bishop1 = new Bishop('w', 'e', 5);
		board.setPiece(bishop1);
		Piece bishop2 = new Bishop('b', 'b', 8);
		board.setPiece(bishop2);
		// board.printBoard();
		System.out.println (bishop2.canMove(board, 'e', 5));
		board.printBoard();

	}

}

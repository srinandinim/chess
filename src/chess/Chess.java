package chess;

import pieces.Piece;
import pieces.Rook;

public class Chess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Board board = new Board();
		// board.printBoard();

		Piece rook1 = new Rook('w', 'a', 1);
		board.setPiece(rook1);
		Piece rook2 = new Rook('b', 'a', 5);
		board.setPiece(rook2);
		System.out.println (rook1.move(board, 'a', 8));
		// System.out.println (rook1.move(board, 'h', 8));

		board.printBoard();

	}

}

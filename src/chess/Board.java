package chess;

import pieces.Piece;

public class Board {

	private Piece[][] board;
	private final int dimension = 9;

	public Board() {
		board = new Piece[dimension][dimension];
	}

	public void initBoard() {

	}

	public void printBoard() {
		for (int i = 1; i < dimension; i++) {
			for (int j = 1; j < dimension; j++) {
				if (board[i][j] != null) {
					System.out.print(board[i][j] + " ");
				} else if (i % 2 != j % 2) {
					System.out.print("##" + " ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println((dimension - i));
		}

		for (int i = 1; i < dimension; i++) {
			System.out.print(" " + (char) (i + 96) + " ");
		}

		System.out.println();
	}

	public Piece getPiece(int row, int col) {
		return board[row][col];
	}

}

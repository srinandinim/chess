package chess;

import pieces.Piece;

public class Board {

	private Piece[][] board;
	private final int dimension = 8;

	public Board() {
		board = new Piece[dimension][dimension];
	}

	public void initBoard() {

	}

	public void printBoard() {
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
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

		for (int i = 0; i < dimension; i++) {
			System.out.print(" " + (char) (i + 97) + " ");
		}

		System.out.println();
	}

	public int getDimension() {
		return dimension;
	}

	public Piece getPiece(char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		return board[rowCoordinate][colCoordinate];
	}

	public void setPiece(Piece piece, char col, int row) {
		int rowCoordinate = dimension - row;
		int colCoordinate = col - 'a';

		board[rowCoordinate][colCoordinate] = piece;
	}

}
